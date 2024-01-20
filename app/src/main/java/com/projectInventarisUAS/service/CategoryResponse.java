package com.projectInventarisUAS.service;

import com.projectInventarisUAS.models.Categories;


import java.util.List;

public class CategoryResponse {
    private boolean success;
    private List<Categories> data;

    public boolean isSuccess() {
        return success;
    }

    public List<Categories> getData() {
        return data;
    }


}
