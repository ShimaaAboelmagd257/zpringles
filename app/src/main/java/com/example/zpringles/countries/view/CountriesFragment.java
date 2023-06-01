package com.example.zpringles.countries.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zpringles.R;
import com.example.zpringles.countries.presenter.CountriesPresenter;
import com.example.zpringles.countries.presenter.CountriesPresenterInterface;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.model.MealModel;
import com.example.zpringles.model.retrofit.Repository;
import com.example.zpringles.NetworkConnection.APIResponse;

import java.util.ArrayList;
import java.util.List;

public class CountriesFragment extends Fragment implements CountriesViewInterface,OnCountriesClickListenterInterface{

    RecyclerView recyclerView;
    TextView textView ;
    String CountryName;
    RecyclerCountriesAdapter recyclerCountriesAdapter;

    CountriesPresenterInterface countriesPresenter;

    GridLayoutManager gridLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
         //CountryName =CountriesFragmentArgs.fromBundle(getArguments()).getMealName();
        if (CountryName != null) {
            textView.setText(CountryName);
        }

        gridLayoutManager=new GridLayoutManager(getContext(),2);
        countriesPresenter=new CountriesPresenter(this, Repository.getInstance(APIResponse.getInstance(getContext()), ConceretLocalSource.getInstance(getContext()),view.getContext()));
        recyclerCountriesAdapter=new RecyclerCountriesAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(recyclerCountriesAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        countriesPresenter.getMeals(CountryName);


    }
    void init(View view){
        recyclerView = view.findViewById(R.id.recyclerCountry);
        textView = view.findViewById(R.id.countryText);
    }

    @Override
    public void ViewCounteryMeal(List<MealModel> models) {
        recyclerCountriesAdapter.setCountryMealModelList(models);
        recyclerCountriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        countriesPresenter.addToFavorite(mealModel);
    }


    @Override
    public void addToFavoriteOnClick(MealModel mealModel) {
        addToFavorite(mealModel);
        Toast.makeText(getContext(), "yuppy Meal is added to favorite", Toast.LENGTH_SHORT).show();
    }
}