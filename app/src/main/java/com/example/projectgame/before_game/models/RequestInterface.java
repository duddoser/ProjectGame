package com.example.projectgame.before_game.models;

import com.example.projectgame.before_game.models.ServerRequest;
import com.example.projectgame.before_game.models.ServerResponse;
import com.example.projectgame.before_game.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {
    @GET("/login")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
