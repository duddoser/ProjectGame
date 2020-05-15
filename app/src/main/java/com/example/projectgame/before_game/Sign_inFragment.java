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
import com.example.projectgame.game.GameFragment;

public class Sign_inFragment extends Fragment implements View.OnClickListener {
    View view;
    public Button btnSign;
    public EditText name, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        btnSign = view.findViewById(R.id.signin_button);
        name = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверка имени и пароля
                ((NavigationHost) getActivity()).navigateTo(new GameFragment(), true);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) { }
}
