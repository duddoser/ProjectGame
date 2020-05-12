package com.example.projectgame.game;

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

public class BuildFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private BuildAdapter adapter;
    public List<String> buildings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_build, container, false);
        addBuildings();
        adapter = new BuildAdapter(getContext(), buildings);
        recyclerView = view.findViewById(R.id.recyclerViewBuild);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void addBuildings(){ // добавить отображение ресурсов
        buildings = new ArrayList<>();
        buildings.add("Tawnhall");
        buildings.add("Barraks");
        buildings.add("Small house");
        buildings.add("Farm");
        buildings.add("House");
        buildings.add("Warehouse");
        buildings.add("Big house");
        buildings.add("Church");
    }
}
