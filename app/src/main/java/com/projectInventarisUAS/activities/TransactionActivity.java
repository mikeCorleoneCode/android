package com.projectInventarisUAS.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projectInventarisUAS.R;
import com.projectInventarisUAS.adapters.TransactionAdapter;
import com.projectInventarisUAS.models.Transaction;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.RetrofitInstance;
import com.projectInventarisUAS.service.TransactionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {
    private static final String TAG = TransactionActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_list);
        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        FloatingActionButton fabCreateItem = findViewById(R.id.fabAddItem);
        fabCreateItem.setOnClickListener(view -> openCreateTransactionActivity());
        Call<TransactionResponse> call = apiService.getListTransaction();

        call.enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if(response.isSuccessful()){
                    TransactionResponse transactionResponse = response.body();
                    if(transactionResponse != null && transactionResponse.isSuccess()){
                        List<Transaction> transactionList = transactionResponse.getData();
                        handleSuccessResponse(transactionList);
                        transactionAdapter = new TransactionAdapter(transactionList);
                        recyclerView.setAdapter(transactionAdapter);
                    }else{
                        handleErrorResponse(response.code());
                    }
                }else{
                    handleErrorResponse(response.code());
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                handleNetworkFailure(t);
            }
        });
    }
    private void openCreateTransactionActivity(){
        Intent intent = new Intent(this, CreateTransactionActivity.class);
        startActivity(intent);
    }
    private void handleSuccessResponse(List<Transaction> transactionList) {
        // Process the list of items as needed
        Log.d(TAG, "Success! Number of items: " + transactionList.size());
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
