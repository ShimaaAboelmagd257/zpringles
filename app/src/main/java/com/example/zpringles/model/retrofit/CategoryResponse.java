package com.example.zpringles.model.retrofit;

import com.example.zpringles.model.retrofit.Category;

import java.util.List;

public class CategoryResponse {

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}