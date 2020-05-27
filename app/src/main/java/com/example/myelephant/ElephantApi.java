package com.example.myelephant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ElephantApi {
    @GET("/api/v2/pokemon")
    Call<RestElephantResponse> getElephantResponse() ;
}
