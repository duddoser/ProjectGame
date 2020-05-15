package com.example.projectgame;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.projectgame.game.GameFragment;
import com.example.projectgame.models.RequestInterface;
import com.example.projectgame.models.ResourceResponse;
import com.example.projectgame.models.ServerRequest;
import com.example.projectgame.models.ServerResponse;
import com.example.projectgame.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProcesses {
    Activity activity;
    Consts consts = new Consts();
    Retrofit retrofit;
    Context context;

    public RetrofitProcesses(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void loginProcess(String username, String password, String email){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(consts.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.login(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ((NavigationHost) activity).navigateTo(new GameFragment(), true);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    public void authProcess(String username, String password){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(consts.AUTH_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.auth(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ((NavigationHost) activity).navigateTo(new GameFragment(), true);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    public void getResources(){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<ResourceResponse> response = requestInterface.resources();
        response.enqueue(new Callback<ResourceResponse>() {
            @Override
            public void onResponse(Call<ResourceResponse> call, Response<ResourceResponse> response) {
                ResourceResponse resp = response.body();
                Log.e("EEEEEE", resp.getWood().toString());
                Toast.makeText(context, resp.getIron(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResourceResponse> call, Throwable t) {

            }
        });


    }
}
