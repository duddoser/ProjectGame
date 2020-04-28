package com.example.projectgame.before_game;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.NavigationHost;
import com.example.projectgame.R;
import com.example.projectgame.before_game.LoginFragment;
import com.example.projectgame.before_game.SettingsFragment;
import com.example.projectgame.game.GameFragment;


public class StartFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Button startButton, settingsButton;
    private MediaPlayer mediaPlayer;
    private SharedPreferences sharedPreferences;

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
            if (checkSharedPref()){
                ((NavigationHost) getActivity()).navigateTo(new GameFragment(), true);
            } else {
                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false);
            }
        }

        if (v == settingsButton){
            ((NavigationHost) getActivity()).navigateTo(new SettingsFragment(), true);
        }

    }

    public boolean checkSharedPref(){
        sharedPreferences = getActivity().getSharedPreferences("loginSettings", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("IS_LOGGED_IN", false)){
            return true;
        }
        return false;
    }
}
