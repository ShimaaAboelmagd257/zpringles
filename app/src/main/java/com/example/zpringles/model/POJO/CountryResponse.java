package com.example.zpringles.model.POJO;

import java.util.List;

public class CountryResponse {


    private List<Country> meals;

    public List<Country> getCountries() {
        return meals;
    }

    public void setCountries(List<Country> countries) {
        this.meals = countries;
    }
}
