package com.example.projectgame.models;

import com.example.projectgame.models.ServerRequest;
import com.example.projectgame.models.ServerResponse;
import com.example.projectgame.models.ServerResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("/login")
    Call<JsonObject> login(@Body HashMap<String, String> json);

    @POST("/auth")
    Call<JsonObject> auth(@Body HashMap<String, String> json); //переделать

    @POST("/get_id")
    Call<JsonObject> get_id(@Body HashMap<String, String> json);

    @POST("/resources")
    Call<ResourceResponse> resources(@Body HashMap<String, String> json);

    @POST("/change_res")
    Call<JsonObject> change_res(@Body HashMap<String, String> json);

    @POST("/get_buildings")
    Call<JsonObject> get_buildings(@Body HashMap<String, String> json);

    @POST("/set_buildings")
    Call<JsonObject> set_buildings(@Body HashMap<String, String> json);
}
