package com.example.zpringles.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.zpringles.R;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.Repository;
import com.example.zpringles.NetworkConnection.APIClient;
import com.example.zpringles.search.presenter.SearchPresenter;
import com.example.zpringles.search.presenter.SearchPresenterInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchClickListener,SearchViewInterface {

    RecyclerView recyclerView;

    SearchCountryAdepter searchCountryAdepter;
    SearchCategoryAdepter searchCategoryAdepter;
    SearchIngredientAdepter searchIngredientAdepter;
    SearchMealAdapter searchMealAdapter ;

    SearchPresenterInterface searchPresenterInterface;

    GridLayoutManager layoutManager;
    List<MealModel> mealModels ;
    List<Country> countries ;
    List<Category> categories ;
    List<Ingredient> ingredients ;


    RadioButton category;
    RadioButton country;
    RadioButton ingrediant;

    SearchView searchView;

    RadioGroup group ;

    boolean flagCategory = false;
    boolean flagCountry = false;
    boolean flagIngredient = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init (view);

        searchPresenterInterface = new SearchPresenter(this ,Repository.getInstance(APIClient.getInstance(getContext()), ConceretLocalSource.getInstance(getContext()),getContext()));

        layoutManager=new GridLayoutManager(getContext(),2);

        searchMealAdapter=new SearchMealAdapter(getContext(),mealModels,this);

        searchCountryAdepter=new SearchCountryAdepter(getContext(),countries,this);

        searchCategoryAdepter=new SearchCategoryAdepter(getContext(),categories,this);

        searchIngredientAdepter=new SearchIngredientAdepter(getContext(),ingredients,this);

        recyclerView.setLayoutManager(layoutManager);

        category.setOnClickListener(event -> {
            System.out.println(flagCategory);
            if (flagCategory == false) {
                categoryOnClick();
                flagCategory = true;
            }else{
                flagCategory = false;
                recyclerView.setAdapter(null);
                group.clearCheck();
                categories = null;
                mealModels = null;
                countries = null;
                ingredients = null;
            }
        });
        country.setOnClickListener(event -> {

            if (flagCountry == false) {
                countryOnClick();
                flagCountry = true;
            }else{
                flagCountry = false;
                recyclerView.setAdapter(null);
                group.clearCheck();
                countries = null;
                categories = null;
                mealModels = null;
                ingredients = null;            }
        });
        ingrediant.setOnClickListener(event -> {
            if (flagIngredient == false) {
                ingredientOnclick();
                flagIngredient = true;
            }else{
                flagIngredient = false;
                group.clearCheck();
                recyclerView.setAdapter(null);
                categories = null;
                mealModels = null;
                countries = null;
                ingredients = null;
            }

        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchView.getQuery().toString().equals("") && group.getCheckedRadioButtonId() == -1){
                    mealModels = null ;
                    recyclerView.setAdapter(null);
                    categories = null;
                    countries = null;
                    ingredients = null;
                }
                if(categories == null && countries == null && ingredients == null & mealModels == null) {
                    searchPresenterInterface.getMealsByName(newText);

                }

                else if(mealModels != null){
                    List<MealModel> result = new ArrayList<>();
                    Observable<MealModel> observable = Observable.fromIterable(mealModels);
                    observable.filter(e -> e.getStrMeal().contains(newText)).subscribe(i->result.add(i));
                    recyclerView.setAdapter(searchMealAdapter);
                    searchMealAdapter.setList(result);
                    searchMealAdapter.notifyDataSetChanged();
                }

                else if(countries != null) {
                    List<Country> result = new ArrayList<>();
                    Observable<Country> observable = Observable.fromIterable(countries);
                    observable.filter(e -> e.getStrArea().contains(newText)).subscribe(i -> result.add(i));
                    recyclerView.setAdapter(searchCountryAdepter);
                    searchCountryAdepter.setList(result);
                    searchCountryAdepter.notifyDataSetChanged();
                }

                else if(categories != null) {
                    List<Category> result = new ArrayList<>();
                    Observable<Category> observable = Observable.fromIterable(categories);
                    observable.filter(e -> e.getStrCategory().contains(newText)).subscribe(i -> result.add(i));
                    recyclerView.setAdapter(searchCategoryAdepter);
                    searchCategoryAdepter.setList(result);
                    searchCategoryAdepter.notifyDataSetChanged();
                }

                else if(ingredients != null) {
                    List<Ingredient> result = new ArrayList<>();
                    Observable<Ingredient> observable = Observable.fromIterable(ingredients);
                    observable.filter(e -> e.getStrIngredient().contains(newText)).subscribe(i -> result.add(i));
                    recyclerView.setAdapter(searchIngredientAdepter);
                    searchIngredientAdepter.setList(result);
                    searchIngredientAdepter.notifyDataSetChanged();
                }
                return false;
            }
        });

    }

    public void init (View view){
        recyclerView = view.findViewById(R.id.recyclerSearch);
        category = view.findViewById(R.id.radioCategory);
        country = view.findViewById(R.id.radioCountry);
        ingrediant = view.findViewById(R.id.radioIngredients);
        group = view.findViewById(R.id.groubSearch);
        searchView = view.findViewById(R.id.search);
    }

    @Override
    public void getMeal(List<MealModel> Meals) {
        recyclerView.setAdapter(searchMealAdapter);
        searchMealAdapter.setList(mealModels);
        searchMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllCategories(List<Category> categories) {
        recyclerView.setAdapter(searchCategoryAdepter);
        this.categories = categories;
        searchCategoryAdepter.setList(categories);
        searchCategoryAdepter.notifyDataSetChanged();
    }

    @Override
    public void getAllCountries(List<Country> countries) {
        recyclerView.setAdapter(searchCountryAdepter);
        this.countries = countries;
        searchCountryAdepter.setList(countries);
        searchCountryAdepter.notifyDataSetChanged();
    }

    @Override
    public void getAllIngredient(List<Ingredient> ingredients) {
        recyclerView.setAdapter(searchIngredientAdepter);
        this.ingredients = ingredients;
        searchIngredientAdepter.setList(ingredients);
        searchIngredientAdepter.notifyDataSetChanged();
    }

    @Override
    public void getMeals(List<MealModel> mealModels) {
        recyclerView.setAdapter(searchMealAdapter);
        this.mealModels = mealModels;
        searchMealAdapter.setList(mealModels);
        searchMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        searchPresenterInterface.addToFavorite(mealModel);
    }

    @Override
    public void categoryItemOnClick(String category) {
        searchPresenterInterface.getMealsByCategories(category);
    }

    @Override
    public void countryItemOnClick(String country) {
        searchPresenterInterface.getMealsByCountries(country);
    }

    @Override
    public void ingredientItemOnclick(String ingredient) {
        searchPresenterInterface.getMealsByIngredients(ingredient);
    }

    @Override
    public void categoryOnClick() {
        searchPresenterInterface.getAllCategories();
    }

    @Override
    public void countryOnClick() {
        searchPresenterInterface.getAllCountries();
    }

    @Override
    public void ingredientOnclick() {
        searchPresenterInterface.getAllIngredient();
    }

    @Override
    public void addToFavoriteOnClick(MealModel mealModel) {
        addToFavorite(mealModel);
        Toast.makeText(getContext(), "Meal is added to favorite", Toast.LENGTH_SHORT).show();
    }
}