package com.example.projectgame.game;

import android.content.Context;
import android.content.res.Resources;
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

import java.util.List;
import java.util.zip.Inflater;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    List<String> material, material2;
    List<Integer> amount, amount2;

    ResourceAdapter(Context context, List<String> material, List<Integer> amount,
                    List<String> material2, List<Integer> amount2) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.material = material;
        this.amount = amount;
        this.material2 = material2;
        this.amount2 = amount2;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String m = material.get(position);
        int num = amount.get(position);
        String m2 = material2.get(position);
        int num2 = amount2.get(position);

        holder.imageView.setImageDrawable(imageSelect(m));
        holder.tvGet.setText(Integer.toString(num));
        holder.imageView2.setImageDrawable(imageSelect(m2));
        holder.tvGive.setText(Integer.toString(num2));
    }

    public Drawable imageSelect(String resource){
        switch (resource) {
            case "wood":
                return this.context.getResources().getDrawable(R.drawable.woods);
            case "stone":
                return this.context.getResources().getDrawable(R.drawable.stone);
            case "metal":
                return this.context.getResources().getDrawable(R.drawable.metal);
            case "iron":
                return this.context.getResources().getDrawable(R.drawable.iron);
        }
        return this.context.getResources().getDrawable(R.drawable.iron);
    }

    @Override
    public int getItemCount() {
        return material.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvGet, tvGive;
        public ImageView imageView, imageView2;
        public Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGet = itemView.findViewById(R.id.tvGet);
            tvGive = itemView.findViewById(R.id.tvGive);
            imageView = itemView.findViewById(R.id.image);
            imageView2 = itemView.findViewById(R.id.image2);
            btn = itemView.findViewById(R.id.btnBuy);
            btn.setOnClickListener(v -> btn.setText("sold"));
        }

        @Override
        public void onClick(View v) {
            Log.e("EVERYTHING IS OK","okkkkkkkkk");
        }
    }
}
