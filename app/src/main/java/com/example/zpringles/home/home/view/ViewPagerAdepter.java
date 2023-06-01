package com.example.zpringles.home.home.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.zpringles.itemPage.view.ItemPageActivity;
import com.example.zpringles.model.MealModel;
import com.example.zpringles.utalites.Helper;

import java.util.List;

public class ViewPagerAdepter extends RecyclerView.Adapter<ViewPagerAdepter.ViewHolder> {

    private final Context context;
    private List<MealModel> list;
    public static final String TAG = "RECYCLER";

    private OnHomeClickLisenterInterface onHomeClickLisenterInterface ;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public CardView cardFavorate ;
        public CardView cardItem;
        public View view;

        public ViewHolder(View v){
            super(v);
            view = v;
            imageView = v.findViewById(R.id.imageItem);
            name = v.findViewById(R.id.itemText);
            cardItem = v.findViewById(R.id.pagerItem);
            cardFavorate = v.findViewById(R.id.itemCardFavorite);

        }

    }

    public ViewPagerAdepter(Context context, List<MealModel> list,OnHomeClickLisenterInterface onHomeClickLisenterInterface) {
        this.context = context;
        this.list = list;
        this.onHomeClickLisenterInterface = onHomeClickLisenterInterface;
    }
    public void setViewPagerAdepterList(List<MealModel> mealModels) {
        this.list = mealModels;
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
                .apply(new RequestOptions().override(500,500)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
        holder.name.setText(list.get(position).getStrMeal());
        holder.cardItem.setOnClickListener(v -> {
            Intent myIntent =new Intent(context, ItemPageActivity.class);
            myIntent.putExtra("MEAL_NAME",list.get(position).getStrMeal());
            context.startActivity(myIntent);
        });

        holder.cardFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                            ((Activity) context).finish();
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
                    onHomeClickLisenterInterface.addToFavoriteOnClick(list.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
