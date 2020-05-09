package com.example.projectgame.game;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectgame.R;

import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {
    public View view;
    private MediaPlayer mP;
    private RecyclerView recyclerView;
    private ResourceAdapter adapter;
    List<String> material, material2;
    List<Integer> amount, amount2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_market, container, false);
        initRecycleAndViews();
        mP = MediaPlayer.create(getContext(), R.raw.opened_door);
        mP.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mP.start();

        return view;
    }

    public void initRecycleAndViews(){
        material = new ArrayList<>();
        material2 = new ArrayList<>();
        amount = new ArrayList<>();
        amount2 = new ArrayList<>();

        material.add("stone");
        material.add("wood");
        material2.add("wood");
        material2.add("stone");
        amount.add(44);
        amount.add(99);
        amount2.add(78);
        amount2.add(87);

        adapter = new ResourceAdapter(getContext(), material, amount, material2, amount2);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
