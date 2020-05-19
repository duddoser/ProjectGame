package com.example.projectgame.before_game;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.projectgame.NavigationHost;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;


/* In SettingsFragment we can log in to another account (if it exists).*/
public class SettingsFragment extends Fragment implements OnBackPressedListener,
        View.OnClickListener {
    public View view;
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        btn = view.findViewById(R.id.btnChangeAcc);
        btn.setOnClickListener(v -> ((NavigationHost) getActivity()).
                navigateTo(new Sign_inFragment(), true));

        return view;
    }

    @Override
    public void onBackPressed() { }

    @Override
    public void onClick(View v) { }
}
