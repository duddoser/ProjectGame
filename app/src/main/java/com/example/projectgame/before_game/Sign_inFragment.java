package com.example.projectgame.before_game;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectgame.NavigationHost;
import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;
import com.example.projectgame.game.GameFragment;

public class Sign_inFragment extends Fragment implements View.OnClickListener {
    View view;
    public Button btnSign;
    public EditText name, password;
    public RetrofitProcesses retrofitProcesses;
    private String u_name, pasw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        btnSign = view.findViewById(R.id.signin_button);
        name = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);

        retrofitProcesses = new RetrofitProcesses(getActivity());
        btnSign.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSign){
            u_name = name.getText().toString();
            pasw = password.getText().toString();
            if (!u_name.isEmpty() && !pasw.isEmpty()){
                retrofitProcesses.authProcess(u_name, pasw, view);
            }
        }
    }
}
