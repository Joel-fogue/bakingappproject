package com.udacity.projects.bakingapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.udacity.projects.bakingapp.AppExecutors;
import com.udacity.projects.bakingapp.data.model.Recipe;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class NetworkDataSource {
    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();
    private final Context mContext;

    @Inject
    ApiInterface apiService;

    private final MutableLiveData<List<Recipe>> mDownloadedRecipes;
    private final AppExecutors mExecutors;

    @Inject
    public NetworkDataSource(Context context, AppExecutors mExecutors) {
        mContext = context.getApplicationContext();
        this.mExecutors = mExecutors;
        mDownloadedRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mDownloadedRecipes;
    }

    public void startFetchRecipeService() {
        Intent intentToFetch = new Intent(mContext, RecipeIntentService.class);
        mContext.startService(intentToFetch);
    }

    public void fetchRecipes() {
        mExecutors.networkIO().execute(() -> {
            Call<List<Recipe>> call = apiService.getRecipe();
            call.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    int statusCode = response.code();
                    List<Recipe> recipes = response.body();
                    if (recipes != null) {
                        Log.d(LOG_TAG, "size " + recipes.size());
                    }
                    mDownloadedRecipes.postValue(recipes);
                }
                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        });
    }
}
