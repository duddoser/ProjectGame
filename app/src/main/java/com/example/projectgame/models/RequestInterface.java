package com.example.projectgame.models;

import com.example.projectgame.models.ServerRequest;
import com.example.projectgame.models.ServerResponse;
import com.example.projectgame.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/login")
    Call<ServerResponse> login(@Body ServerRequest request);

    @GET("/auth")
    Call<ServerResponse> auth(@Body ServerRequest request);

    @GET("/resources")
    Call<ResourceResponse> resources();
}
