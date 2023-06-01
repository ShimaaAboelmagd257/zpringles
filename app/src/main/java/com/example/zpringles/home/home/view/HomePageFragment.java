package com.example.zpringles.home.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.zpringles.R;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.home.home.presenter.HomePagePresenter;
import com.example.zpringles.home.home.presenter.HomePresenter;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.Repository;
import com.example.zpringles.NetworkConnection.APIClient;

import java.util.ArrayList;
import java.util.List;

public  class HomePageFragment extends Fragment implements HomeViewInterface,OnHomeClickLisenterInterface {

    RecyclerView mealRecyclerView;
    RecyclerView category;
    RecyclerView country;

    public static List<MealModel> favorite;
    public static List<List<MealModel>> plans;
    HomePresenter homePagePresenter;
    LinearLayoutManager mealLayoutManager, CategorieLayoutManager,countryLayoutManager;
    RecycleCountryAdepter recycleCountryAdepter;
    RecycleCategoryAdepter recycleCategoryAdepter;
    RecyclerRandomAdapter recyclerRandomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        mealLayoutManager = new LinearLayoutManager(view.getContext());
        CategorieLayoutManager = new LinearLayoutManager(view.getContext());
        countryLayoutManager = new LinearLayoutManager(view.getContext());

        recycleCountryAdepter = new RecycleCountryAdepter(view.getContext(),new ArrayList<Country>());
        recycleCategoryAdepter = new RecycleCategoryAdepter(view.getContext(),new ArrayList<Category>());
        recyclerRandomAdapter = new RecyclerRandomAdapter(view.getContext(),new ArrayList<>(),this);

        homePagePresenter=new HomePagePresenter(this, Repository.getInstance(APIClient.getInstance(getContext()
        ), ConceretLocalSource.getInstance(getContext()),view.getContext()));

        mealRecyclerView.setLayoutManager(mealLayoutManager);
        mealRecyclerView.setAdapter(recyclerRandomAdapter);

        category.setLayoutManager(CategorieLayoutManager);
        category.setAdapter(recycleCategoryAdepter);

        country.setLayoutManager(countryLayoutManager);
        country.setAdapter(recycleCountryAdepter);

        mealRecyclerView.setHasFixedSize(true);
        category.setHasFixedSize(true);
        country.setHasFixedSize(true);

        mealLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        CategorieLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        countryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        homePagePresenter.getRandomMeal();
        homePagePresenter.getCategoriesList();
        homePagePresenter.getCountriesList();
        /*
        to be continued and show random meal  daily inspiretion
        *   recyclerView = view.findViewById(R.id.myRecView);
        recyclerView1.setHasFixedSize(true);
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager.setMaxVisibleItems(3);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new HomeFragmentPresenter(Repository.getInstance(API_Client.getInstance()
                , ConcreteLocalSource.getInstance(getContext())
                , getContext())
                , this);
        presenter.getRandomMeals();
        adapter1 = new HomeFragmentAdapter(HomeFragment.this.getContext(), mealDetails, this);
        recyclerView1.setAdapter(adapter1);*/

        if (favorite != null && favorite.size() != 0 ){
            for (int i=0 ;i<favorite.size() ;i++){
                insertDataInRoom(favorite.get(i));
            }
            favorite.clear();
        }

        if(plans != null && plans.size() > 0) {
            for (int i = 0; i < plans.size(); i++) {
                for (int j = 0; j < plans.get(i).size(); j++) {
                    insertDataInRoom(plans.get(i).get(j));
                }
            }
            plans.clear();
        }



    }
     private void initUI(@NonNull View view){
         mealRecyclerView = view.findViewById(R.id.viewPager);
         category = view.findViewById(R.id.categoryRecycler);
         country = view.findViewById(R.id.countryRecycler);

    }
    @Override
    public void ViewRandomMeal(List<MealModel> models) {
        recyclerRandomAdapter.setViewPagerAdepterList(models);
        recyclerRandomAdapter.notifyDataSetChanged();
    }

    @Override
    public void ViewCountriesList(List<Country> models) {
        System.out.println("All Categories :"+models.get(0).getStrArea());
        recycleCountryAdepter.setRecycleCountryAdepterList(models);
        recycleCountryAdepter.notifyDataSetChanged();
    }

    @Override
    public void ViewCategoriesList(List<Category> models) {

        recycleCategoryAdepter.setCategoryModelList(models);
        recycleCategoryAdepter.notifyDataSetChanged();
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        homePagePresenter.addToFavorite(mealModel);

    }

    @Override
    public void insertDataInRoom(MealModel mealModel) {
        homePagePresenter.insertDataInRoom(mealModel);
    }


    @Override
    public void addToFavoriteOnClick(MealModel mealModel) {

    }

    @Override
    public void nevToItemPage(MealModel model) {

    }
}