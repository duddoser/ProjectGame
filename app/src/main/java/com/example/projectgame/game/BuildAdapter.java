package com.example.projectgame.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
    public Context context;
    public List<String> buildings;
    public List<Integer> amount;
    public LayoutInflater layoutInflater;
    public Map <String, Map<String, Integer>> resources;
    public BuildAndResourceConstants consts = new BuildAndResourceConstants();

    public BuildAdapter(@NonNull Context context, List<String> buildings,
                        Map<String, Map<String, Integer>> resources) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.buildings = buildings;
        this.resources = resources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerviewbuild, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String b = this.buildings.get(position);
        holder.textView.setText(b);
        holder.imageView.setImageDrawable(imageSelect(b));

        List<String> materials = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        for (Map.Entry<String, Integer> map: resources.get(b).entrySet()){
            materials.add(map.getKey());
            amount.add(map.getValue().toString());
        }

        holder.imgRes1.setImageDrawable(imageSelect(materials.get(0)));
        holder.imgRes2.setImageDrawable(imageSelect(materials.get(1)));
        holder.imgRes3.setImageDrawable(imageSelect(materials.get(2)));

        holder.tvRes1.setText(amount.get(0));
        holder.tvRes2.setText(amount.get(1));
        holder.tvRes3.setText(amount.get(2));
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btnBuild;
        TextView textView, tvRes1, tvRes2, tvRes3;
        ImageView imageView, imgRes1, imgRes2, imgRes3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBuild = itemView.findViewById(R.id.btnBuild);
            textView = itemView.findViewById(R.id.buildingName);
            imageView = itemView.findViewById(R.id.img);
            tvRes1 = itemView.findViewById(R.id.tvRes1);
            tvRes2 = itemView.findViewById(R.id.tvRes2);
            tvRes3 = itemView.findViewById(R.id.tvRes3);
            imgRes1 = itemView.findViewById(R.id.res1);
            imgRes2 = itemView.findViewById(R.id.res2);
            imgRes3 = itemView.findViewById(R.id.res3);
            btnBuild.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.btnBuild.setText(R.string.Built);
        }
    }

    public Drawable imageSelect(String resource) {
        switch (resource) {
            case "Shack":
                return this.context.getResources().getDrawable(R.drawable.house3);
            case "House":
                return this.context.getResources().getDrawable(R.drawable.house);
            case "Big house":
                return this.context.getResources().getDrawable(R.drawable.house2);
            case "Warehouse":
                return this.context.getResources().getDrawable(R.drawable.ambar);
            case "Farm":
                return this.context.getResources().getDrawable(R.drawable.farm);
            case "Tawnhall":
                return this.context.getResources().getDrawable(R.drawable.tawnhall);
            case "Church":
                return this.context.getResources().getDrawable(R.drawable.church);
            case "Barraks":
                return this.context.getResources().getDrawable(R.drawable.kazarmy);
            case "wood":
                return this.context.getResources().getDrawable(R.drawable.woods);
            case "stone":
                return this.context.getResources().getDrawable(R.drawable.stone);
            case "metal":
                return this.context.getResources().getDrawable(R.drawable.metal);
            case "iron":
                return this.context.getResources().getDrawable(R.drawable.iron);
        }
        return null;
    }
}
