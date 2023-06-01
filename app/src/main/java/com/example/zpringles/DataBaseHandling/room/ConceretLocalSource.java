package com.example.zpringles.DataBaseHandling.room;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.example.zpringles.model.POJO.MealModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ConceretLocalSource implements LocalSource{

    private static ConceretLocalSource conceretLocalSource = null;
    private MealDao mealDao ;

    Single<List<MealModel>> mealFavorites;


    private ConceretLocalSource(Context context){

        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDao = appDataBase.mealDao();
        mealFavorites = mealDao.getFavorites();


    }

    public static ConceretLocalSource getInstance(Context context){

        if(conceretLocalSource == null){
            conceretLocalSource = new ConceretLocalSource(context);
        }
        return conceretLocalSource;
    }

    @Override
    public void insertFavorite(MealModel mealModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    mealDao.insertFavorite(mealModel);
                }catch (SQLiteConstraintException e){

                }

            }
        }).start();
    }

    @Override
    public void removeFavorite(MealModel mealModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteFavorite(mealModel);
            }
        }).start();
    }

    @Override
    public Single<List<MealModel>> getAllStoredFavorites() {
        return mealFavorites;
    }

    @Override
    public void insertPlan(MealModel mealModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    mealDao.insertPlan(mealModel);
                }catch (SQLiteConstraintException e){

                }

            }
        }).start();

    }

    @Override
    public void removePlan(MealModel mealModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deletePlan(mealModel);
            }
        }).start();

    }

    @Override
    public Single<List<MealModel>> getAllStoredPlans(String day) {
        Single<List<MealModel>> planMeals =mealDao.getPlans(day);
        return planMeals;
    }


    @Override
    public void deleteAllMeals() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteAllMeals();
            }
        }).start();

    }
}
