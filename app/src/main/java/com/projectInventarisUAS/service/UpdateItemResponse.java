package com.projectInventarisUAS.service;

public class UpdateItemResponse {
    private boolean error;
    private String message;
    public boolean isError(){
        return error;
    }
    public String getMessage(){
        return message;
    }
}
