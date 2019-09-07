package com.udacity.projects.bakingapp.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.udacity.projects.bakingapp.BakingApplication;

import javax.inject.Inject;

public class RecipeIntentService extends IntentService {

    private static final String LOG_TAG = RecipeIntentService.class.getSimpleName();

    @Inject
    NetworkDataSource networkDataSource;
    public RecipeIntentService() {
        super("RecipeIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BakingApplication.getComponent(this).inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        networkDataSource.fetchRecipes();
    }
}
