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
import androidx.fragment.app.DialogFragment;
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
import java.util.Random;

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
    private Random r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        sharedPref = getActivity().getSharedPreferences("loginSettings",
                Context.MODE_PRIVATE);
        startTime = System.currentTimeMillis();
        init_views();
        retrofitProcesses = new RetrofitProcesses(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Maps map = new Maps(this, savedInstanceState);
        districtRes = map.getDistrict();
        authentification();
    }

    public void init_views(){
        consts = new Consts();
        r = new Random();

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
            DialogFragment fragment = new NewTradeFragment(this);
            fragment.show(getFragmentManager(), "trade");
        } else if (v == btnDig){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime)/1000;
            if (duration >= 5){
                startTime = System.currentTimeMillis();
                int num = r.nextInt(10) + 15;
                updateResource(num);
                Snackbar.make(view, "You earned " + Integer.toString(num) + " " + districtRes
                                + "!!!", Snackbar.LENGTH_SHORT).show();
                setOneResource(districtRes, num);
            } else {
                Snackbar.make(view, "Wait for 5 second and you'll get your resource!",
                        Snackbar.LENGTH_LONG).show();
            }
        } else if (v == btnBuild){
            ((NavigationHost) getActivity()).navigateTo(new BuildFragment(), true);
        }
    }

    public void authentification(){
        setResources();
    }

    public void makeSnackbar(){
        Snackbar.make(view, "It is too little! Amount of resource you need should" +
                        " be less or equal amount of resource you give",
                Snackbar.LENGTH_LONG).show();
    }

    public void setResources(){
        retrofitProcesses.getResources(sharedPref.getString("USER_ID", "1"),
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

    public void setOneResource(String res, int amount){
        if (res.equals(consts.WOOD)){
            int text = Integer.parseInt(btnWood.getText().toString());
            btnWood.setText(String.valueOf(text + amount));
        } else if (res.equals(consts.IRON)){
            int text = Integer.parseInt(btnIron.getText().toString());
            btnIron.setText(String.valueOf(text + amount));
        } else if (res.equals(consts.METAL)){
            int text = Integer.parseInt(btnMetal.getText().toString());
            btnMetal.setText(String.valueOf(text + amount));
        } else if (res.equals(consts.STONE)){
            int text = Integer.parseInt(btnStone.getText().toString());
            btnStone.setText(String.valueOf(text + amount));
        }
    }

    public void updateResource(int n){
        SharedPreferences sharedPref = getActivity().
                getSharedPreferences("loginSettings", Context.MODE_PRIVATE);
        String user_id = sharedPref.getString("USER_ID", "1111");
        retrofitProcesses.changeResource(user_id, districtRes, n);
    }
}