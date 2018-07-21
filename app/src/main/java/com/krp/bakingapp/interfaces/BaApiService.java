package com.krp.bakingapp.interfaces;

import com.krp.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaApiService {

    // To get list of recipes
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipeList();

}
