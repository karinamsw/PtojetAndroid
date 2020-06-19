package com.example.myelephant.presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myelephant.Constants;
import com.example.myelephant.R;
import com.example.myelephant.data.ElephantApi;
import com.example.myelephant.presentation.controller.MainController;
import com.example.myelephant.presentation.model.Elephant;
import com.example.myelephant.presentation.model.RestElephantResponse;
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

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
               this,
                new GsonBuilder().setLenient().create(),
                getSharedPreferences("application_elephant", Context.MODE_PRIVATE)

        );
        controller.onStart();

    }

    public void showList (List <Elephant> elephantList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
         recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(elephantList);
        recyclerView.setAdapter(mAdapter);
        }



    public void showError (){
            Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

        }
    }
