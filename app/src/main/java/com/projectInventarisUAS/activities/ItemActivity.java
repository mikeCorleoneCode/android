package com.projectInventarisUAS.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectInventarisUAS.R;
import com.projectInventarisUAS.adapters.ItemAdapter;
import com.projectInventarisUAS.models.Item;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.ItemResponse;
import com.projectInventarisUAS.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {
    private static final String TAG = ItemActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabCreateItem = findViewById(R.id.fabAddItem);
        fabCreateItem.setOnClickListener(view -> openCreateItemActivity());

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        Call<ItemResponse> call = apiService.getListItem();

        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if(response.isSuccessful()){
                    ItemResponse itemResponse = response.body();
                    if(itemResponse != null && itemResponse.isSuccess()) {
                        List<Item> itemList = itemResponse.getData();
                        handleSuccessResponse(itemList);
                        itemAdapter = new ItemAdapter(itemList);
                        recyclerView.setAdapter(itemAdapter);
                    }else{
                        handleErrorResponse(response.code());
                    }
                }else{
                    handleErrorResponse(response.code());
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                handleNetworkFailure(t);
            }
        });
    }
    private void openCreateItemActivity(){
        Intent intent = new Intent(this, CreateItemActivity.class);
        startActivity(intent);
    }
    private void handleSuccessResponse(List<Item> itemList) {
        // Process the list of items as needed
        Log.d(TAG, "Success! Number of items: " + itemList.size());
        // Update UI or perform other actions based on the successful response
    }

    private void handleErrorResponse(int httpErrorCode) {
        // Handle different HTTP error codes
        Log.e(TAG, "Error! HTTP Code: " + httpErrorCode);
        // Update UI or show error messages based on the HTTP error
    }

    private void handleNetworkFailure(Throwable t) {
        // Handle network-related errors
        Log.e(TAG, "Network failure! Error: " + t.getMessage());
        // Update UI or show error messages for network-related issues
    }
}
