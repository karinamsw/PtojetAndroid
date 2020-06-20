package com.example.myelephant.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myelephant.R;
import com.example.myelephant.presentation.controller.MainController;
import com.example.myelephant.presentation.model.Elephant;
import com.google.gson.GsonBuilder;

import java.util.List;

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

        mAdapter = new ListAdapter(elephantList, new ListAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(Elephant item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
        }



    public void showError (){
            Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

        }

    public void navigateToDetails(Elephant elephant) {
        Toast.makeText(getApplicationContext(), "to do navigate", Toast.LENGTH_SHORT).show();
    }
}
