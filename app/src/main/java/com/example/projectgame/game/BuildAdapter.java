package com.example.projectgame.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
    public Context context;
    public List<String> buildings;
    public List<Integer> amount;
    public LayoutInflater layoutInflater;
    public Map <String, Map<String, Integer>> resources;
    public BuildAndResourceConstants consts = new BuildAndResourceConstants();
    Map<String, Integer> flags;
    public String user_id;
    private BuildFragment buildFragment;

    public BuildAdapter(@NonNull Context context, List<String> buildings,
                        Map<String, Map<String, Integer>> resources, Map<String, Integer> flags,
                        String user_id, BuildFragment buildFragment) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.buildings = buildings;
        this.resources = resources;
        this.flags = flags;
        this.user_id = user_id;
        this.buildFragment = buildFragment;
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

        Log.e("BuildAdapter", b);
        Log.e("Building", String.valueOf(flags.get(b.toLowerCase())));
        if ((b.equals(consts.BIG_HOUSE) && flags.get("big_house") == 1) ||
                (!b.equals(consts.BIG_HOUSE) && flags.get(b.toLowerCase()) == 1)) {
            holder.btnBuild.setText(R.string.Built);
            holder.btnBuild.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button btnBuild, btnIron, btnWood, btnMetal, btnStone;
        TextView textView, tvRes1, tvRes2, tvRes3;
        ImageView imageView, imgRes1, imgRes2, imgRes3;
        RetrofitProcesses retrofitProcesses;

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
            retrofitProcesses = new RetrofitProcesses();
        }

        @Override
        public void onClick(View v) {
            retrofitProcesses.getResources(user_id, new RetrofitProcesses.ResourceCallbacks() {
                @Override
                public void onGetResources(Map<String, Integer> reses) {
                    int r1 = Integer.parseInt(tvRes1.getText().toString());
                    int r2 = Integer.parseInt(tvRes2.getText().toString());
                    int r3 = Integer.parseInt(tvRes3.getText().toString());

                    //not optimised but works properly
                    if (r1 == 100 && r2 == 180 && r3 == 200){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.TAWNHALL.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -100);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -180);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -200);
                            buildFragment.updateResources(consts.METAL, consts.WOOD, consts.STONE,
                                    -100, -180, -200);
                        }
                    } else if (r1 == 150 && r2 == 200 && r3 == 240){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.IRON) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.BARRAKS.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -150);
                            retrofitProcesses.changeResource(user_id, consts.IRON, -200);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -240);
                            buildFragment.updateResources(consts.METAL, consts.IRON, consts.STONE,
                                    -150, -200, -240);
                        }
                    } else if (r1 == 150 && r2 == 210 && r3 == 240){
                        if ((reses.get(consts.IRON) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.SHACK.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.IRON, -150);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -210);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -240);
                            buildFragment.updateResources(consts.IRON, consts.WOOD, consts.STONE,
                                    -150, -210, -240);
                        }
                    } else if (r1 == 300 && r2 == 340 && r3 == 300){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.FARM.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -300);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -340);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -300);
                            buildFragment.updateResources(consts.METAL, consts.WOOD, consts.STONE,
                                    -300, -340, -300);
                        }
                    } else if (r1 == 300 && r2 == 400 && r3 == 350){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.HOUSE.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -300);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -400);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -350);
                            buildFragment.updateResources(consts.METAL, consts.WOOD, consts.STONE,
                                    -300, -400, -350);
                        }
                    } else if (r1 == 300 && r2 == 400 && r3 == 400){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.IRON) >= r2) &&
                                (reses.get(consts.WOOD) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.WAREHOUSE.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -300);
                            retrofitProcesses.changeResource(user_id, consts.IRON, -340);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -300);
                            buildFragment.updateResources(consts.METAL, consts.IRON, consts.WOOD,
                                    -300, -340, -300);
                        }
                    } else if (r1 == 300 && r2 == 250 && r3 == 450){
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, "big_house");
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -300);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -250);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -450);
                            buildFragment.updateResources(consts.METAL, consts.WOOD, consts.STONE,
                                    -300, -250, -450);
                        }
                    } else if (r1 == 550 && r2 == 510 && r3 == 600) {
                        if ((reses.get(consts.METAL) >= r1) && (reses.get(consts.WOOD) >= r2) &&
                                (reses.get(consts.STONE) >= r3)){
                            retrofitProcesses.set_buildings(user_id, consts.CHURCH.toLowerCase());
                            btnBuild.setText(R.string.Built);
                            btnBuild.setEnabled(false);
                            retrofitProcesses.changeResource(user_id, consts.METAL, -550);
                            retrofitProcesses.changeResource(user_id, consts.WOOD, -510);
                            retrofitProcesses.changeResource(user_id, consts.STONE, -600);
                            buildFragment.updateResources(consts.METAL, consts.WOOD, consts.STONE,
                                    -550, -510, -600);
                        }
                    }

                }
            });
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
