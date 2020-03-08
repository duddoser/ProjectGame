package com.example.projectgame;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StartFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Button startButton, settingsButton;
    // key = AIzaSyAeYuBs3a_jNV76ZmQ2FYEPkcM3u_zXwUc


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start, container, false);
        startButton = view.findViewById(R.id.start_button);
        settingsButton = view.findViewById(R.id.settings_button);

        startButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == startButton){
            ((NavigationHost) getActivity()).navigateTo(new GameFragment(), false);
        }

        if (v == settingsButton){
            ((NavigationHost) getActivity()).navigateTo(new SettingsFragment(), false);
        }

    }
}
