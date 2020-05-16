package com.example.projectgame.game;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.Consts;
import com.example.projectgame.NavigationHost;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;
import com.example.projectgame.models.RequestInterface;
import com.example.projectgame.models.ResourceResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GameFragment extends Fragment implements OnBackPressedListener, View.OnClickListener {
    private View view;
    private Button btnMarket, btnDig, btnBuild;
    private Button btnWood, btnIron, btnMetal, btnStone;
    private String districtRes;
    private Consts consts;
    private long startTime, endTime, duration;
    private RetrofitProcesses retrofitProcesses;
    private Retrofit retrofit;
    private SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        sharedPref = getActivity().getSharedPreferences("loginSettings", Context.MODE_PRIVATE);
        startTime = System.currentTimeMillis();
        init_views();
        retrofitProcesses = new RetrofitProcesses(getActivity());
        Maps map = new Maps(this, savedInstanceState);
        districtRes = map.getDistrict();
        Log.e("OOOOOO", districtRes);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authentification();
    }

    public void init_views(){
        consts = new Consts();

        btnMarket = view.findViewById(R.id.btnMarket);
        btnMarket.setOnClickListener(this);
        btnDig = view.findViewById(R.id.btnDig);
        btnDig.setOnClickListener(this);
        btnBuild = view.findViewById(R.id.btnBuild);
        btnBuild.setOnClickListener(this);
        btnWood = view.findViewById(R.id.btnW);
        btnIron = view.findViewById(R.id.btnI);
        btnMetal = view.findViewById(R.id.btnM);
        btnStone = view.findViewById(R.id.btnS);
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onClick(View v) {
        if (v == btnMarket){
            ((NavigationHost) getActivity()).navigateTo(new MarketFragment(), true);
        } else if (v == btnDig){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime)/1000;
            if (duration >= 10){
                startTime = System.currentTimeMillis();
                updateResource();
                setResources();
                Snackbar.make(view, "You erned 10 " + districtRes + "!!!",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else if (v == btnBuild){
            ((NavigationHost) getActivity()).navigateTo(new BuildFragment(), true);
        }
    }

    public void authentification(){
        Snackbar.make(view, "Hello " + sharedPref.getString("NAME", "admin"),
                Snackbar.LENGTH_SHORT).show();
        String user = sharedPref.getString("NAME", "admin");
        String password = sharedPref.getString("PASSWORD", "admin");

        retrofitProcesses.getId(user, password, new RetrofitProcesses.IdCallback() {
            @Override
            public void onGetId(String user_id) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("USER_ID", user_id);
                editor.apply();
            }
        });
        setResources();
    }

    public void setResources(){
        retrofitProcesses.getResources(sharedPref.getString("USER_ID", "1111"),
                new RetrofitProcesses.ResourceCallbacks() {
                    @Override
                    public void onGetResources(Map<String, Integer> reses) {
                        btnWood.setText(reses.get(consts.WOOD).toString());
                        btnIron.setText(reses.get(consts.IRON).toString());
                        btnMetal.setText(reses.get(consts.METAL).toString());
                        btnStone.setText(reses.get(consts.STONE).toString());
                    }
                });
    }

    public void updateResource(){
        SharedPreferences sharedPref = getActivity().
                getSharedPreferences("loginSettings", Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("USER_ID", "1111");
        retrofitProcesses.changeResource(user_id, districtRes, 10);
    }
}