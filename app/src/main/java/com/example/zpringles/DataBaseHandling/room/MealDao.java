package com.example.zpringles.DataBaseHandling.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zpringles.model.MealModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {
    @Query("SELECT * From Meals where isFavorite = '1'")
    Single<List<MealModel>> getFavorites() ;
    @Insert
    void insertFavorite (MealModel mealModel);
    @Delete
    void deleteFavorite (MealModel mealModel);
    @Query("SELECT * From Meals where nameDay =:day")
    Single<List<MealModel>> getPlans(String day) ;
    @Insert
    void insertPlan (MealModel mealModel);
    @Delete
    void deletePlan (MealModel mealModel);
    @Query("DELETE FROM Meals")
    public void deleteAllMeals() ;

}
