package com.example.projectgame.before_game;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.projectgame.Consts;
import com.example.projectgame.NavigationHost;
import com.example.projectgame.R;
import com.example.projectgame.before_game.models.RequestInterface;
import com.example.projectgame.before_game.models.ServerRequest;
import com.example.projectgame.before_game.models.ServerResponse;
import com.example.projectgame.before_game.models.User;
import com.example.projectgame.game.GameFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnLogin, btnCancel;
    private EditText username, password, email;
    private SharedPreferences sharedPreferences;
    public Consts consts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.login_button);
        btnCancel = view.findViewById(R.id.cancel_button);

        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        username = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel){
            // возврфщаемся обратно
        } else if (v == btnLogin){
            retrofitProcess(this.username.getText().toString(), this.password.getText().toString(),
                    this.email.getText().toString());
            //((NavigationHost) getActivity()).navigateTo(new GameFragment(), true);
        }
    }

    public void loginUser(){
        sharedPreferences = getActivity().getSharedPreferences("loginSettings",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("IS_LOGGED_IN", true);
        editor.putString("E-MAIL", email.getText().toString());
        editor.putString("NAME", username.getText().toString());
        editor.putString("PASSWORD", password.getText().toString());
        editor.apply();
    }

    public void retrofitProcess(String name, String passw, String mail){
//        Log.e("CHECK", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//
//        Retrofit retrofit = new Retrofit.Builder().
//                baseUrl(consts.BASE_URL + consts.LOGIN).
//                addConverterFactory(GsonConverterFactory.create()).build();
//        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
//
//        Log.e("RETROFIT", "CREATED SDFGSDFGDFGDFGSFDG");
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(passw);
//        user.setEmail(mail);
//        ServerRequest request = new ServerRequest();
//        request.setOperation(consts.LOGIN_OPERATION);
//        request.setUser(user);
//        Call<ServerResponse> response = requestInterface.operation(request);
//
//        response.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                ServerResponse serverResponse = response.body(); // надо посмотреть как проходит связь с сервером
//                if (serverResponse.getResult().equals(consts.SUCCESS)){
//                    sharedPreferences = getActivity().getSharedPreferences("loginSettings",
//                            Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                    editor.putBoolean("IS_LOGGED_IN", true);
//                    editor.putString("E-MAIL", email.getText().toString());
//                    editor.putString("NAME", username.getText().toString());
//                    editor.putString("PASSWORD", password.getText().toString());
//                    editor.apply();
//
//                    ((NavigationHost) getActivity()).navigateTo(new TestFragment(), true);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//
//            }
//        });
    }
}
