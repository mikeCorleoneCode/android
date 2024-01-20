package com.projectInventarisUAS.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectInventarisUAS.R;
import com.projectInventarisUAS.adapters.CategoryAdapter;
import com.projectInventarisUAS.models.Categories;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.CategoryResponse;

import com.projectInventarisUAS.service.RetrofitInstance;

import java.util.List;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class CategoryActivity extends AppCompatActivity {
    private static final String TAG = CategoryActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);
        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fabCreateCategory = findViewById(R.id.fabAddItem);
        fabCreateCategory.setOnClickListener(view -> openCreateCategory());
        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        Call<CategoryResponse> call = apiService.getListCategories();

        call.enqueue(new Callback<CategoryResponse>(){
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response)
            {
                if(response.isSuccessful()){
                    CategoryResponse categoryResponse = response.body();
                    if(categoryResponse != null && categoryResponse.isSuccess()){
                        List<Categories> categoriesList =  categoryResponse.getData();
                        handleSuccessResponse(categoriesList);
                        categoryAdapter = new CategoryAdapter(categoriesList);
                        recyclerView.setAdapter(categoryAdapter);
                    }else{
                        handleErrorResponse(response.code());
                    }
                }else{
                    handleErrorResponse(response.code());
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t){
                handleNetworkFailure(t);
            }
        });
    }
    private void openCreateCategory(){
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivity(intent);
    }
    private void handleSuccessResponse(List<Categories> categoriesList){
        Log.d(TAG, "Success! Number of Categories: " + categoriesList.size());
    }

    private void handleErrorResponse(int httpErrorCode){
        Log.e(TAG, "Error! HTTP CODE: " + httpErrorCode);
    }

    private void handleNetworkFailure(Throwable t){
        Log.e(TAG, "Network failure! Error: " + t.getMessage());
    }
}
