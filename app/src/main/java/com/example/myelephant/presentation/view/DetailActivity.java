package com.example.myelephant.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelephant.R;
import com.example.myelephant.Singletons;
import com.example.myelephant.presentation.controller.MainController;
import com.example.myelephant.presentation.model.Elephant;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    private TextView txtDetailFictional;
    private TextView txtDetailId;
    private TextView txtDetailAffiliation;
    private TextView txtDetailDob;
    private TextView txtDetailDod;
    private TextView txtDetailIndex;
    private TextView txtDetailNote;
    private TextView txtDetailSex;
    private TextView txtDetailSpecies;
    private TextView txtDetailWikilink;
    private ImageView ImageDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById((R.id.detail_name));
        txtDetailFictional= findViewById((R.id.detail_fictional));
        txtDetailId = findViewById((R.id.detail_id));
        txtDetailAffiliation = findViewById((R.id.detail_affiliation));
        txtDetailDob = findViewById((R.id.detail_dob));
        txtDetailDod = findViewById((R.id.detail_dod));
        txtDetailFictional = findViewById((R.id.detail_fictional));
        txtDetailIndex = findViewById((R.id.detail_index));
        txtDetailNote = findViewById((R.id.detail_note));
        txtDetailSex = findViewById((R.id.detail_sex));
        txtDetailSpecies = findViewById((R.id.detail_species));
        txtDetailWikilink = findViewById((R.id.detail_wikilink));
        ImageDetail = findViewById(R.id.icon);

        Intent intent = getIntent();
        String elephantJson = intent.getStringExtra("elephantKey");
        Elephant elephant = Singletons.getGson().fromJson(elephantJson,Elephant.class);
        showDetail (elephant);
    }
    private void showDetail(Elephant elephant) {
        txtDetail.setText(elephant.getName());
        txtDetailFictional.setText("Fictional : " +elephant.getFictional());
       txtDetailId.setText("Id : "+elephant.getId());
        txtDetailAffiliation.setText("Affiliation : "+elephant.getAffiliation());
        txtDetailDod.setText("Date of birth : "+elephant.getDob());
        txtDetailDob.setText("Date of death : "+elephant.getDod());
        txtDetailIndex.setText("Index : "+elephant.getIndex());
        txtDetailNote.setText("Note : "+elephant.getNote());
        txtDetailSex.setText("Sex : "+elephant.getSex());
        txtDetailSpecies.setText("Spices : "+elephant.getSpecies());
        txtDetailWikilink.setText("Wikilink : "+elephant.getWikilink());
        Picasso.get().load(elephant.getImage()).into(ImageDetail);

    }

}
