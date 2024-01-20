package com.projectInventarisUAS.service;

public class CreateCategoryResponse{
    private boolean error;
    private String message;
    public boolean isError(){
        return error;
    }
    public String getMessage(){
        return message;
    }
}
