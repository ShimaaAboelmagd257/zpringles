package com.example.zpringles.search.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zpringles.R;
import com.example.zpringles.model.retrofit.Ingredient;

import java.util.List;

public class SearchIngredientAdepter extends RecyclerView.Adapter<SearchIngredientAdepter.ViewHolder> {

    private final Context context;
    private List<Ingredient> list;
    public static final String TAG = "RECYCLER";
    SearchClickListener searchClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public CardView cardItem;
        public View view;

        public ViewHolder(View v){
            super(v);
            view = v;
            imageView = v.findViewById(R.id.imageCategory);
            name = v.findViewById(R.id.categoryName);
            cardItem = v.findViewById(R.id.cardCategory);

        }
    }

    public SearchIngredientAdepter(Context context, List<Ingredient> list,SearchClickListener searchClickListener) {
        this.context = context;
        this.list = list;
        this.searchClickListener = searchClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.searchitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+list.get(position).getStrIngredient()+".png")
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
        holder.name.setText(list.get(position).getStrIngredient());
        holder.cardItem.setOnClickListener(event ->
                searchClickListener.ingredientItemOnclick(list.get(position).getStrIngredient()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Ingredient> list){

        this.list = list;
    }
}


