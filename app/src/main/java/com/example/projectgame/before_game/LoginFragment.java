package com.example.projectgame.before_game;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectgame.NavigationHost;
import com.example.projectgame.R;
import com.example.projectgame.game.GameFragment;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnLogin, btnCancel;
    private EditText username, password, email;
    private SharedPreferences sharedPreferences;

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
            loginUser();
            ((NavigationHost) getActivity()).navigateTo(new GameFragment(), true);
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
}
