package com.example.projectgame.game;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;


public class GameFragment extends Fragment implements OnBackPressedListener{
    private View view;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        Button btn1 = view.findViewById(R.id.btnI);
        new Maps(this, savedInstanceState);
        return view;
    }

    @Override
    public void onBackPressed() {
        getChildFragmentManager().popBackStack();
    }

}