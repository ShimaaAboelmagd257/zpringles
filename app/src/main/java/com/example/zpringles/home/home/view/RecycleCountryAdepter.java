package com.example.zpringles.home.home.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zpringles.R;

import com.example.zpringles.model.retrofit.Country;
import com.example.zpringles.model.retrofit.Country;

import java.util.ArrayList;
import java.util.List;

public class RecycleCountryAdepter extends RecyclerView.Adapter<RecycleCountryAdepter.ViewHolder> {

    private final Context context;
    private List<Country> list;
    public static final String TAG = "RECYCLER";
    String [] countriesFlags;

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
    public void setRecycleCountryAdepterList(List<Country> CountryList) {
        this.list = CountryList;
    }

    public RecycleCountryAdepter(Context context, ArrayList<Country> list) {
        this.context = context;
        this.list = list;
       countriesFlags=context.getResources().getStringArray(R.array.flags);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoryitem,parent,false);
        ViewHolder viewHolder = new RecycleCountryAdepter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(countriesFlags[position])
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.imageView);
        holder.name.setText(list.get(position).getStrArea());
        //holder.cardItem.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View v) {
                ActionHomePageFragmentToCountriesFragment action= HomePageFragmentDirections.actionHomePageFragmentToCountriesFragment(list.get(position).getStrArea());
                Navigation.findNavController(v).navigate(action);
            }
        });*/
    }//

    @Override
    public int getItemCount() {
        return list.size();
    }
}


