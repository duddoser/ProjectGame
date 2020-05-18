package com.example.projectgame.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildFragment extends Fragment implements OnBackPressedListener {
    View view;
    private RecyclerView recyclerView;
    private BuildAdapter adapter;
    private Button btnWood, btnIron, btnMetal, btnStone;
    private List<String> buildings1;
    private Map<String, Integer> map1, map2, map3, map4, map5, map6, map7, map8;
    private Map<String, Map<String, Integer>> resources;
    public BuildAndResourceConstants consts = new BuildAndResourceConstants();
    private RetrofitProcesses retrofitProcesses;
    private Map<String, Integer> flags;
    private SharedPreferences sharedPref;
    private BuildFragment buildFragment = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_build, container, false);
        retrofitProcesses = new RetrofitProcesses(getActivity());
        sharedPref = getActivity().getSharedPreferences("loginSettings",
                Context.MODE_PRIVATE);
        addResources();
        addBuildings();
        initButtons();
        setResources();
        setFlags();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initButtons(){
        btnIron = view.findViewById(R.id.btnI);
        btnWood = view.findViewById(R.id.btnW);
        btnMetal = view.findViewById(R.id.btnM);
        btnStone = view.findViewById(R.id.btnS);
    }

    private void addBuildings(){
        buildings1 = new ArrayList<>();
        buildings1.add(consts.TAWNHALL);
        buildings1.add(consts.BARRAKS);
        buildings1.add(consts.SHACK);
        buildings1.add(consts.FARM);
        buildings1.add(consts.HOUSE);
        buildings1.add(consts.WAREHOUSE);
        buildings1.add(consts.BIG_HOUSE);
        buildings1.add(consts.CHURCH);
    }

    public void addResources(){
        resources = new HashMap<>();

        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map3 = new HashMap<>();
        map4 = new HashMap<>();
        map5 = new HashMap<>();
        map6 = new HashMap<>();
        map7 = new HashMap<>();
        map8 = new HashMap<>();
        map1.put(consts.STONE, 200);
        map1.put(consts.WOOD, 180);
        map1.put(consts.METAL, 100);
        map2.put(consts.STONE, 240);
        map2.put(consts.IRON, 200);
        map2.put(consts.METAL, 150);
        map3.put(consts.STONE, 240);
        map3.put(consts.WOOD, 210);
        map3.put(consts.IRON, 150);
        map4.put(consts.WOOD, 340);
        map4.put(consts.METAL, 300);
        map4.put(consts.STONE, 300);
        map5.put(consts.WOOD, 400);
        map5.put(consts.STONE, 350);
        map5.put(consts.METAL, 300);
        map6.put(consts.WOOD, 400);
        map6.put(consts.IRON, 400);
        map6.put(consts.METAL, 300);
        map7.put(consts.STONE, 450);
        map7.put(consts.METAL, 300);
        map7.put(consts.WOOD, 250);
        map8.put(consts.STONE, 600);
        map8.put(consts.METAL, 550);
        map8.put(consts.WOOD, 510);

        resources.put(consts.TAWNHALL, map1);
        resources.put(consts.BARRAKS, map2);
        resources.put(consts.SHACK, map3);
        resources.put(consts.FARM, map4);
        resources.put(consts.HOUSE, map5);
        resources.put(consts.WAREHOUSE, map6);
        resources.put(consts.BIG_HOUSE,map7);
        resources.put(consts.CHURCH, map8);
    }

    public void setFlags(){
        String user_id = sharedPref.getString("USER_ID", "1");//ставить ресурсы
        flags = new HashMap<>();
        retrofitProcesses.get_buildings(user_id, new RetrofitProcesses.BuildingsCallbacks() {
            @Override
            public void onGetBuildings(Map<String, Double> buildings) {
                for (String item: buildings.keySet()){
                    flags.put(item, buildings.get(item).intValue());
                }
                adapter = new BuildAdapter(getContext(), buildings1, resources, flags, user_id,
                        buildFragment);
                recyclerView = view.findViewById(R.id.recyclerViewBuild);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
        });

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

    public void updateResources(String res1, String res2, String res3, int n1, int n2, int n3){
        if (res1.equals(consts.METAL)){
            int text = Integer.parseInt(btnMetal.getText().toString());
            btnMetal.setText(String.valueOf(text + n1));
        } else if (res1.equals(consts.IRON)){
            int text = Integer.parseInt(btnIron.getText().toString());
            btnIron.setText(String.valueOf(text + n1));
        }

        if (res2.equals(consts.WOOD)){
            int text = Integer.parseInt(btnWood.getText().toString());
            btnWood.setText(String.valueOf(text + n2));
        } else if (res2.equals(consts.IRON)){
            int text = Integer.parseInt(btnIron.getText().toString());
            btnIron.setText(String.valueOf(text + n2));
        }

        if (res3.equals(consts.STONE)){
            int text = Integer.parseInt(btnStone.getText().toString());
            btnStone.setText(String.valueOf(text + n3));
        } else if (res3.equals(consts.WOOD)){
            int text = Integer.parseInt(btnWood.getText().toString());
            btnWood.setText(String.valueOf(text + n3));
        }
    }


    @Override
    public void onBackPressed() { }
}
