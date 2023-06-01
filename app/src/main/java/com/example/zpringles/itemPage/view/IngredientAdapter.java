package com.example.zpringles.itemPage.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zpringles.R;
import com.example.zpringles.model.IngredientModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    Context context;
    List<IngredientModel> model;
    IngredientAdapter(Context context, List<IngredientModel> mealModelList){
        this.context=context;
        this.model=mealModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.ingredient_recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.meal_name.setText(model.get(position).getName());
        Glide.with(context).load(model.get(position).getImg())
                .apply(new RequestOptions().override(holder.meal_image.getWidth(),holder.meal_image.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_image);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView meal_name;
        CircleImageView meal_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_name =itemView.findViewById(R.id.ingredient_meal_name);
            meal_image =itemView.findViewById(R.id.profileUserImage);

        }
    }
}



