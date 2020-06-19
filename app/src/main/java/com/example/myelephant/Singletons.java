package com.example.myelephant;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myelephant.data.ElephantApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static ElephantApi elephantApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static ElephantApi getElephantApi() {
        if (elephantApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            elephantApiInstance = retrofit.create(ElephantApi.class);
        }
        return elephantApiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstances(Context context) {
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("application_elephant", Context.MODE_PRIVATE);

        }
        return sharedPreferencesInstance;

    }
}
