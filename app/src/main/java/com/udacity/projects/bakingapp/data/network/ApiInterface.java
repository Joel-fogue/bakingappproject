package com.udacity.projects.bakingapp.data.network;

import com.udacity.projects.bakingapp.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("baking.json")
    Call<List<Recipe>> getRecipe();
}
