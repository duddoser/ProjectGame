package com.example.projectgame.game;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectgame.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private BuildAdapter adapter;
    private List<String> buildings;
    private Map<String, Integer> map1, map2, map3, map4, map5, map6, map7, map8;
    private Map<String, Map<String, Integer>> resources;
    public BuildAndResourceConstants consts = new BuildAndResourceConstants();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_build, container, false);
        addResources();
        addBuildings();
        adapter = new BuildAdapter(getContext(), buildings, resources);
        recyclerView = view.findViewById(R.id.recyclerViewBuild);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void addBuildings(){
        buildings = new ArrayList<>();
        buildings.add(consts.TAWNHALL);
        buildings.add(consts.BARRAKS);
        buildings.add(consts.SHACK);
        buildings.add(consts.FARM);
        buildings.add(consts.HOUSE);
        buildings.add(consts.WAREHOUSE);
        buildings.add(consts.BIG_HOUSE);
        buildings.add(consts.CHURCH);
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
}
