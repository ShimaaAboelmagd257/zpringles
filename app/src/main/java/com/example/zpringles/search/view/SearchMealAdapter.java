package com.example.zpringles.search.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zpringles.R;
import com.example.zpringles.Authantication.signup.view.SignupActivity;
import com.example.zpringles.itemPage.view.ItemPageActivity;
import com.example.zpringles.model.MealModel;
import com.example.zpringles.utalites.Helper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchMealAdapter extends RecyclerView.Adapter<SearchMealAdapter.ViewHolder> {
    Context context;
    List<MealModel> list;

    SearchClickListener searchClickListener;

    public SearchMealAdapter(Context context, List<MealModel> mealModelList,SearchClickListener searchClickListener){
        this.context=context;
        this.list =mealModelList;
        this.searchClickListener = searchClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.mealitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.meal_name.setText(list.get(position).getStrMeal());
        Glide.with(context).load(list.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(holder.meal_image.getWidth(),holder.meal_image.getHeight()))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_image);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent =new Intent(context, ItemPageActivity.class);
                myIntent.putExtra("MEAL_NAME",list.get(holder.getAbsoluteAdapterPosition()).getStrMeal());
                context.startActivity(myIntent);
            }
        });


        holder.btnFav.setOnClickListener( event -> {
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
                searchClickListener.addToFavoriteOnClick(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MealModel> list){

        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView meal_name;
        CircleImageView meal_image;
        ImageButton btnFav;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_name =itemView.findViewById(R.id.mealName);
            meal_image =itemView.findViewById(R.id.mealImage);
            btnFav =itemView.findViewById(R.id.mealFav);
            item=itemView.findViewById(R.id.planmealitem);

        }
    }
}

