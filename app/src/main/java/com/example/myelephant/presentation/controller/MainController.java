package com.example.myelephant.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myelephant.Constants;
import com.example.myelephant.data.ElephantApi;
import com.example.myelephant.presentation.model.Elephant;
import com.example.myelephant.presentation.model.RestElephantResponse;
import com.example.myelephant.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart() {


        List<Elephant> elephantList = getDataFromCache();

        if (elephantList != null) {
            view.showList(elephantList);
        } else {
            makeApiCall();
        }
    }

        private void makeApiCall () {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ElephantApi elephantApi = retrofit.create(ElephantApi.class);

            Log.d("VINCE", "BEFORE CALLBACK");
            Call<RestElephantResponse> call = elephantApi.getElephantResponse();
            call.enqueue(new Callback<RestElephantResponse>() {


                @Override
                public void onResponse(Call<RestElephantResponse> call, Response<RestElephantResponse> response) {
                    Log.d("VINCE", "INSIDE CALLBACK");

                    if (response.isSuccessful() && response.body() != null) {
                        List<Elephant> elephantList = response.body().getResults();
                        saveList(elephantList);
                        view.showList(elephantList);
                        //Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();

                    } else {
                        view.showError();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestElephantResponse> call, Throwable t) {
                    view.showError();
                }
            });
        }

    private void saveList(List<Elephant> elephantList) {
        String jsonString = gson.toJson(elephantList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_ELEPHANT_LIST, jsonString)
                .apply();


    }


    private List<Elephant> getDataFromCache() {
        String jsonElephant = sharedPreferences.getString(Constants.KEY_ELEPHANT_LIST, null);

        if (jsonElephant == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Elephant>>() {
            }.getType();
            return gson.fromJson(jsonElephant, listType);
        }
    }




    public void onItemClick(Elephant elephant){
        view.navigateToDetails(elephant);

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
