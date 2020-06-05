package com.example.myelephant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ElephantApi {
    @GET("falseApi.json")
    Call<RestElephantResponse> getElephantResponse();
}
