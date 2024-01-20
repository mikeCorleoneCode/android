package com.projectInventarisUAS.models;

import com.google.gson.annotations.SerializedName;

public class Categories {

    public Categories(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("categoryName")
    private String categoryName;
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



}
