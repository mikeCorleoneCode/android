package com.projectInventarisUAS.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projectInventarisUAS.R;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.CreateCategoryResponse;
import com.projectInventarisUAS.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryActivity extends AppCompatActivity {
    private EditText editTextNama;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_add);

        editTextNama = findViewById(R.id.editTextText);
        Button submitButton = findViewById(R.id.button);

        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        submitButton.setOnClickListener(view -> createCategory());
    }

    private void createCategory(){
        String nama = editTextNama.getText().toString().trim();
        Call<CreateCategoryResponse> call = apiService.createCategory(nama);
        call.enqueue(new Callback<CreateCategoryResponse>(){
            @Override
            public void onResponse(Call<CreateCategoryResponse> call, Response<CreateCategoryResponse> response){
                if(response.isSuccessful()){
                    showToast("Category created successfully");
                    finish();
                }else{
                    showToast("Error Creating Category");
                }
            }
            @Override
            public void onFailure(Call<CreateCategoryResponse> call, Throwable t){
                showToast("Network Failure: " + t.getMessage());
            }
        });
    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
