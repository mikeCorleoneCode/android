package com.projectInventarisUAS.service;

public class CreateItemResponse{
    private boolean error;
    private String message;
    public boolean isError(){
        return error;
    }
    public String getMessage(){
        return message;
    }
}
