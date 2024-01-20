package com.projectInventarisUAS.models;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("itemId")
    private int itemId;
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("name")
    private String name;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("price")
    private int price;

    public Item(int categoryId, String Name, String ImagePath, int Price) {
        this.categoryId = categoryId;
        this.name = Name;
        this.imagePath = ImagePath;
        this.price = Price;
    }
    // Constructor, getters, and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
