package com.example.projectgame.before_game;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.autofill.OnClickAction;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        btnSign = view.findViewById(R.id.signin_button);
        name = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);
        String u_name = name.getText().toString();
        String pasw = password.getText().toString();

        retrofitProcesses = new RetrofitProcesses(getActivity());
        btnSign.setOnClickListener(v -> {
            if (!u_name.isEmpty() && !pasw.isEmpty()){
               // retrofitProcesses.authProcess(u_name, pasw);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) { }
}
