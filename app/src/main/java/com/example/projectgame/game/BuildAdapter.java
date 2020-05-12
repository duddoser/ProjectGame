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

import java.util.List;

public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
    public Context context;
    public List<String> buildings;
    public LayoutInflater layoutInflater;

    public BuildAdapter(@NonNull Context context, List<String> buildings) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.buildings = buildings;
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
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btnBuild;
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBuild = itemView.findViewById(R.id.btnBuild);
            textView = itemView.findViewById(R.id.buildingName);
            imageView = itemView.findViewById(R.id.img);
            btnBuild.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.btnBuild.setText(R.string.Built);
        }
    }

    public Drawable imageSelect(String resource) {
        switch (resource) {
            case "Small house":
                return this.context.getResources().getDrawable(R.drawable.house);
            case "House":
                return this.context.getResources().getDrawable(R.drawable.house2);
            case "Big house":
                return this.context.getResources().getDrawable(R.drawable.house3);
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
        }
        return null;
    }
}
