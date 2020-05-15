package com.example.projectgame.game;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.MediaPlayerInterface;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;

import java.util.List;

public class MarketFragment extends Fragment implements OnBackPressedListener, View.OnClickListener {
    public View view;
    private MediaPlayer mP;
    private Button btnNewTrade;
    private RecyclerView recyclerView;
    private ResourceAdapter adapter;
    private List<String> material, material2;
    private List<Integer> amount, amount2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_market, container, false);
        setTrades();
        initRecycleAndViews();
        ((MediaPlayerInterface) getActivity()).pauseMediaPlayer();
        mP = MediaPlayer.create(getContext(), R.raw.opened_door);
        mP.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mP.start();
        return view;
    }

    public void initRecycleAndViews(){
        adapter = new ResourceAdapter(getContext(), material, amount, material2, amount2);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        btnNewTrade = view.findViewById(R.id.newTrade);
        btnNewTrade.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onClick(View v) {
        if (v == btnNewTrade){
            DialogFragment fragment = new NewTradeFragment();
            fragment.show(getFragmentManager(), "trade");
        }
    }

    public void setTrades(){
        // доделать подключение к sql то же самое в newtradefragment
    }
}

