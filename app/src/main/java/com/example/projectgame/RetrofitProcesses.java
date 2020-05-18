package com.example.projectgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectgame.game.BuildAndResourceConstants;
import com.example.projectgame.game.GameFragment;
import com.example.projectgame.models.MessageResponse;
import com.example.projectgame.models.RequestInterface;
import com.example.projectgame.models.ResourceResponse;
import com.example.projectgame.models.ServerRequest;
import com.example.projectgame.models.ServerResponse;
import com.example.projectgame.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.maps.DirectionsApi;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* RetrofitProcesses class has methods that simplifies work with connection to the server*/

public class RetrofitProcesses {
    Activity activity;
    Consts consts = new Consts();
    Retrofit retrofit;
    Map<String, Integer> resources;

    // Interfaces that helps to return values from onResume()
    public interface ResourceCallbacks{ void onGetResources(Map<String, Integer> reses);}
    public interface IdCallback{ void onGetId(String user_id);}
    public interface BuildingsCallbacks{ void onGetBuildings(Map<String, Double> buildings);}

    // Class's constructor
    public RetrofitProcesses(Activity activity){
        this.activity = activity;
        retrofit = new Retrofit.Builder()
                .baseUrl(consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitProcesses(){
        retrofit = new Retrofit.Builder()
                .baseUrl(consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //loginProcess is used in LoginFragment and adds new user to database
    public void loginProcess(String username, String password, String email, View view){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        HashMap<String, String> json = new HashMap<>();
        json.put("username", username);
        json.put("password", password);
        json.put("email", email);

        Call<JsonObject> response = requestInterface.login(json);

        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("Response", response.raw().toString());

                if (response.body().get("message").toString().contains("success")){
                    loginUser(email, username, password);
                    Snackbar.make(view, "Hello, " + username + "!!!", Snackbar.LENGTH_LONG).show();
                    ((NavigationHost) activity).navigateTo(new GameFragment(), true);
                } else {
                    Snackbar.make(view, "Account with this name already exists",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // authProcess is used in Sign_inFragment to connect to the server
    public void authProcess(String username, String password, View view){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        HashMap<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        Call<JsonObject> response = requestInterface.auth(user);

        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("AUUUUUUUUTH", response.raw().toString());
                if (response.body().get("message").toString().contains("success")){
                    getId(username, password, new IdCallback() {
                        @Override
                        public void onGetId(String user_id) {
                            SharedPreferences sharedPreferences = activity.getSharedPreferences(
                                    "loginSettings", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.clear();
                            editor.putBoolean("IS_LOGGED_IN", true);
                            editor.putString("E-MAIL", "nothing"); //it's ok as email is never used
                            editor.putString("NAME", username);
                            editor.putString("PASSWORD", password);
                            editor.putString("USER_ID", user_id);
                            editor.apply();

                            Snackbar.make(view, "Hello, " + username + "!!!",
                                    Snackbar.LENGTH_SHORT).show();
                            ((NavigationHost) activity).navigateTo(new GameFragment(),
                                    true);
                        }
                    });
                } else {
                    Snackbar.make(view, "No account was found :(",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    //getResources helps to return resource values from database
    public void getResources(String user_id, final ResourceCallbacks resourceCallbacks){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        HashMap<String, String> json = new HashMap<>();
        json.put("user_id", user_id);

        Call<ResourceResponse> response = requestInterface.resources(json);
        response.enqueue(new Callback<ResourceResponse>() {
            @Override
            public void onResponse(Call<ResourceResponse> call, Response<ResourceResponse>
                    response) {
                resources = new HashMap<>();
                ResourceResponse resp = response.body();
                Log.i("Response", response.raw().toString());
                setResources(resp.getWood(), resp.getIron(), resp.getMetal(), resp.getStone());
                resourceCallbacks.onGetResources(resources);
            }

            public void setResources(int wood, int iron, int metal, int stone){
                resources.put(consts.WOOD, wood);
                resources.put(consts.IRON, iron);
                resources.put(consts.METAL, metal);
                resources.put(consts.STONE, stone);
            }

            @Override
            public void onFailure(Call<ResourceResponse> call, Throwable t) {
            }
        });
    }

    //loginUser adds new user's information to sharedPreferences
    public void loginUser(String email, String username, String password){
        getId(username, password, new IdCallback() {
            @Override
            public void onGetId(String user_id) {
                SharedPreferences sharedPreferences = activity.getSharedPreferences(
                        "loginSettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();

                editor.putBoolean("IS_LOGGED_IN", true);
                editor.putString("E-MAIL", email);
                editor.putString("NAME", username);
                editor.putString("PASSWORD", password);

                editor.putString("USER_ID", user_id);
                editor.apply();
            }
        });
    }

    //getId helps to return user_id by username and password
    public void getId(String username, String password, IdCallback idCallback){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        HashMap<String, String> json = new HashMap<>();
        json.put("username", username);
        json.put("password", password);

        Call<JsonObject> response = requestInterface.get_id(json);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("Response Id", response.raw().toString());
                idCallback.onGetId(response.body().get("user_id").toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    //changeResource changes amount of resource
    public void changeResource(String user_id, String res, int amount){
        getResources(user_id, new ResourceCallbacks() {
            @Override
            public void onGetResources(Map<String, Integer> reses) {
                RequestInterface requestInterface = retrofit.create(RequestInterface.class);
                HashMap<String, String> json = new HashMap<>();
                json.put("user_id", user_id);
                json.put("resource", res);
                json.put("amount", Integer.toString(reses.get(res) + amount));

                Call<JsonObject> response = requestInterface.change_res(json);
                response.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }

    //get_buildings helps to get buildings from server
    public void get_buildings(String user_id, BuildingsCallbacks buildingsCallbacks){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        HashMap<String, String> json = new HashMap<>();
        json.put("user_id", user_id);
        Call<JsonObject> response = requestInterface.get_buildings(json);

        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("Response", response.raw().toString());
                Map<String, Double> resp = new Gson().fromJson(response.body().toString(),
                        Map.class);
                buildingsCallbacks.onGetBuildings(resp);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) { }
        });
    }


    public void set_buildings(String user_id, String building){
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        HashMap<String, String> json = new HashMap<>();
        json.put("user_id", user_id);
        json.put("building", building);
        Call<JsonObject> response = requestInterface.set_buildings(json);

        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("SEEEEET", response.raw().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
