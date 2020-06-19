package com.example.myelephant.data;

import com.example.myelephant.presentation.model.RestElephantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ElephantApi {
    @GET("ElephantApi.json")
    Call<RestElephantResponse> getElephantResponse();
}
