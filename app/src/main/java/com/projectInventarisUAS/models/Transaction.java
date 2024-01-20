package com.projectInventarisUAS.models;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("transactionId")
    private int transactionId;
    @SerializedName("itemId")
    private Integer itemId;  // Use Integer to handle the case when it's null
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("totalPrice")
    private int totalPrice;
    @SerializedName("timestamp")
    private String timestamp;
    public Transaction(int transactionId, Integer itemId, int quantity, int totalPrice, String timestamp) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }




}
