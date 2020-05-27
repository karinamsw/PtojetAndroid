package com.example.myelephant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com/karinamsw/PtojetAndroid/master/app/src/main/java/com/example/myelephant/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("aaplication_elephant", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Elephant> elephantList = getDataFromCache();
        if (elephantList != null) {
            showList(elephantList);
        } else {
            makeApiCall();
        }
    }

    private List<Elephant> getDataFromCache() {

        String jsonElephant = sharedPreferences.getString("jsonElephantList", null);

        if (jsonElephant == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Elephant>>() {
            }.getType();
            return gson.fromJson(jsonElephant, listType);
        }


    }


    private void showList(List<Elephant> elephantList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(elephantList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ElephantApi pokeApi = retrofit.create(ElephantApi.class);

        Log.d("VINCE", "BEFORE CALLBACK");
        Call<RestElephantResponse> call = pokeApi.getElephantResponse();
        call.enqueue(new Callback<RestElephantResponse>() {

            @Override
            public void onResponse(Call<RestElephantResponse> call, Response<RestElephantResponse> response) {
                Log.d("VINCE", "INSIDE CALLBACK");


                if (response.isSuccessful() && response.body() != null) {
                    List<Elephant> elephantList = response.body().getResults();
                    saveList(elephantList);
                    showList(elephantList);
                } else {
                    showError();
                }

            }


            @Override

            public void onFailure(@NonNull Call<RestElephantResponse> call, Throwable t) {
                showError();

            }
        });
        Log.d("VINCE", "AFTER CALLBACK");

    }

    private void saveList(List<Elephant> elephantList) {
        String jsonString = gson.toJson(elephantList);

        sharedPreferences
                .edit()
                .putString("jsonElephantList", jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

    }
}