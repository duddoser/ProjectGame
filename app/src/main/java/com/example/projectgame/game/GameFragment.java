package com.example.projectgame.game;


import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.NavigationHost;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;


public class GameFragment extends Fragment implements OnBackPressedListener, View.OnClickListener {
    private View view;
    private Button btnMarket, btnDig, btnBuild;
    private String districtRes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("BBBBBBBB", "VVVVVVVVVVVVVVVVV");
        view = inflater.inflate(R.layout.fragment_game, container, false);
        init_views();
        Maps map = new Maps(this, savedInstanceState);
        //districtRes = map.new District(map.get_location()).getDistrict();
        return view;
    }

    public void init_views(){
        btnMarket = view.findViewById(R.id.btnMarket);
        btnMarket.setOnClickListener(this);
        btnDig = view.findViewById(R.id.btnDig);
        btnDig.setOnClickListener(this);
        btnBuild = view.findViewById(R.id.btnBuild);
        btnBuild.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() { getChildFragmentManager().popBackStack(); }

    @Override
    public void onClick(View v) {
        if (v == btnMarket){
            ((NavigationHost) getActivity()).navigateTo(new MarketFragment(), true);
        } else if (v == btnDig){
            //add to user
        } else if (v == btnBuild){
            ((NavigationHost) getActivity()).navigateTo(new BuildFragment(), true);
        }
    }
}