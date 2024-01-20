package com.projectInventarisUAS.service;

import com.projectInventarisUAS.models.Transaction;

import java.util.List;

public class TransactionResponse {
    private boolean success;
    private List<Transaction> data;

    public boolean isSuccess() {
        return success;
    }

    public List<Transaction> getData() {
        return data;
    }
}
