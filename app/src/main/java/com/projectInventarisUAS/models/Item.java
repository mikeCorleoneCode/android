package com.projectInventarisUAS.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {
    @SerializedName("itemId")
    private int itemId;
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private int price;

    public Item(int categoryId, String name, int price) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
    }

    protected Item(Parcel in) {
        itemId = in.readInt();
        categoryId = in.readInt();
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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




    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeInt(categoryId);
        dest.writeString(name);
        dest.writeInt(price);
    }
}
