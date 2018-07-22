package com.krp.bakingapp.common;

import com.krp.bakingapp.interfaces.BaApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rakesh Praneeth.
 * This class contains the basic url that the project is handling for each API.
 * It contains a method that returns Retrofit object.
 * It contains a static method to create BaApiService using Retrofit.
 */
public final class BaseUrl {

    private BaseUrl(){

    }

    // It's value cannot be accessed and changed in the project.
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    // This method is used to build a Retrofit object using Base Url and convert factory.
    private static Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Creates an ApiService.
    public static BaApiService getBaApiService(){
        return BaseUrl.getRetrofit().create(BaApiService.class);
    }
}
