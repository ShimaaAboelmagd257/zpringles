package com.example.zpringles.home.home.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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
import com.example.zpringles.Authantication.signup.view.SignupActivity;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.utalites.Helper;

import java.util.List;

public class RecyclerRandomAdapter extends RecyclerView.Adapter<RecyclerRandomAdapter.ViewHolder> {

    private final Context context;
    private List<MealModel> list;
    public static final String TAG = "RECYCLER";
    String [] pagersFlags;
    private OnHomeClickLisenterInterface onHomeClickLisenterInterface ;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public CardView cardFavorate ;
        public CardView cardItem;
        public View view;
        //private OnClickListener listener;
        public ViewHolder(View itemView){
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imageItem);
            name = itemView.findViewById(R.id.itemText);
            cardItem = itemView.findViewById(R.id.pagerItem);
            cardFavorate = itemView.findViewById(R.id.itemCardFavorite);

        }

    }
    public void setViewPagerAdepterList(List<MealModel> mealModels) {
        this.list = mealModels;
    }

    public RecyclerRandomAdapter(Context context, List<MealModel> list, OnHomeClickLisenterInterface onHomeClickLisenterInterface) {
        this.context = context;
        this.list = list;
        this.onHomeClickLisenterInterface = onHomeClickLisenterInterface;
        pagersFlags =context.getResources().getStringArray(R.array.flags);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pageritem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getStrMealThumb())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground).into(holder.imageView);
        holder.name.setText(list.get(position).getStrMeal());


         /* holder.cardFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });*/

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignupActivity.class);
                intent.putExtra("mealName" , list.get(holder.getAdapterPosition()).getStrMeal());
                intent.putExtra("mealID" , list.get(holder.getAdapterPosition()).getIdMeal());
                intent.putExtra("mealthumb" ,list.get(holder.getAdapterPosition()).getStrMealThumb());
                Log.i(TAG, "onClick: "+list.get(holder.getAbsoluteAdapterPosition()).getStrMeal());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
