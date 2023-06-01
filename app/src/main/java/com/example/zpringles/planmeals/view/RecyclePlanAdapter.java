package com.example.zpringles.planmeals.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zpringles.R;
import com.example.zpringles.Authantication.signup.view.SignupActivity;
import com.example.zpringles.itemPage.view.ItemPageActivity;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.utalites.Helper;

import java.util.List;

public class RecyclePlanAdapter extends RecyclerView.Adapter<RecyclePlanAdapter.ViewHolder> {

    private final Context context;
    private List<MealModel> list;
    public static final String TAG = "RECYCLER";
    OnPlanClickListner onplanClickListner;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public CardView cardItem;
        public View view;
        Button remove_from_plan;
        ImageButton addToFav;

        public ViewHolder(View v){
            super(v);
            view = v;
            addToFav=v.findViewById(R.id.mealFav);
            remove_from_plan=v.findViewById(R.id.remove_btn);

            imageView = v.findViewById(R.id.mealImage);
            name = v.findViewById(R.id.mealName);
            cardItem = v.findViewById(R.id.planmealitem);

        }

    }
    public void setRecyclePlanAdapterList(List<MealModel> models){
        list=models;
    }

    public RecyclePlanAdapter(Context context, List<MealModel> list,OnPlanClickListner onplanClickListner) {
        this.onplanClickListner=onplanClickListner;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.planmealsitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    public void removePlan(MealModel mealModel){
        this.list.remove(mealModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(500,500)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
        holder.name.setText(list.get(position).getStrMeal());

        holder.remove_from_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onplanClickListner.onRemovePlanClicked(list.get(position));
            }
        });
        holder.addToFav.setOnClickListener(event ->{

            if(Helper.SKIP == "skip"){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to signup in application?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("yes, Signup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, SignupActivity.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                });


                builder.setNegativeButton("No, thanks", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else {
                list.get(position).setFavorite(true);
                list.get(position).setNameDay("Not");
                onplanClickListner.addToFavoriteOnClick(list.get(position));
            }
        });


        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =new Intent(context, ItemPageActivity.class);
                myIntent.putExtra("MEAL_NAME",list.get(position).getStrMeal());
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}