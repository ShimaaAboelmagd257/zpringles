package com.example.zpringles.model.modelFirebase;

import com.example.zpringles.model.POJO.MealModel;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class UserModel {
    String image;
    String userName;
    String email;
    String passWord;
    List<MealModel> Favorites;
    List<MealModel> Saturday;
    List<MealModel> Sunday;
    List<MealModel> Monday;
    List<MealModel> Tuesday;
    List<MealModel> Thursday;
    List<MealModel> Wednesday;
    List<MealModel> Friday;

    public List<MealModel> getSaturday() {
        return Saturday;
    }

    public void setSaturday(List<MealModel> saturday) {
        Saturday = saturday;
    }

    public List<MealModel> getSunday() {
        return Sunday;
    }

    public void setSunday(List<MealModel> sunday) {
        Sunday = sunday;
    }

    public List<MealModel> getMonday() {
        return Monday;
    }

    public void setMonday(List<MealModel> monday) {
        Monday = monday;
    }

    public List<MealModel> getTuesday() {
        return Tuesday;
    }

    public void setTuesday(List<MealModel> tuesday) {
        Tuesday = tuesday;
    }

    public List<MealModel> getThursday() {
        return Thursday;
    }

    public void setThursday(List<MealModel> thursday) {
        Thursday = thursday;
    }

    public List<MealModel> getWednesday() {
        return Wednesday;
    }

    public void setWednesday(List<MealModel> wednesday) {
        Wednesday = wednesday;
    }

    public List<MealModel> getFriday() {
        return Friday;
    }

    public void setFriday(List<MealModel> friday) {
        Friday = friday;
    }

    public UserModel(String image, String userName, String email, String passWord, List<MealModel> favorites, List<MealModel> saturdayPlan, List<MealModel> sundayPlan, List<MealModel> mondayPlan, List<MealModel> tuesdayPlan, List<MealModel> thursdayPlan, List<MealModel> wednesdayPlan, List<MealModel> fridayPlan) {
        this.image = image;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        Favorites = favorites;
        Saturday = saturdayPlan;
        Sunday = sundayPlan;
        Monday = mondayPlan;
        Tuesday = tuesdayPlan;
        Thursday = thursdayPlan;
        Wednesday = wednesdayPlan;
        Friday = fridayPlan;
    }

    public List<MealModel> getFavorites() {
        return Favorites;
    }

    public void setFavorites(List<MealModel> favorites) {
        Favorites = favorites;
    }

    public UserModel(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
