package com.projectInventarisUAS.service;

public class CreateTransactionResponse{
    private boolean error;
    private String message;
    public boolean isError(){
        return error;
    }
    public String getMessage(){
        return message;
    }
}
