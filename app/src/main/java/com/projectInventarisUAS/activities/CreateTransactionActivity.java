package com.projectInventarisUAS.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projectInventarisUAS.R;
import com.projectInventarisUAS.models.Item;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.CreateTransactionResponse;
import com.projectInventarisUAS.service.ItemResponse;
import com.projectInventarisUAS.service.RetrofitInstance;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTransactionActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView textViewTotalPrice;
    private NumberPicker numberPickerQuantity;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_add);

        spinner = findViewById(R.id.spinner);
        numberPickerQuantity = findViewById(R.id.numberPickerQuantity);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        Button submitButton = findViewById(R.id.button);

        apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        fetchItems();
        numberPickerQuantity.setMinValue(1);
        numberPickerQuantity.setMaxValue(10);
        numberPickerQuantity.setValue(1);

        numberPickerQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // Handle the quantity change
                updateTotalPrice(newVal);
            }
        });

        submitButton.setOnClickListener(view -> createTransaction());
    }
    private void fetchItems(){
        Call<ItemResponse> call = apiService.getListItem();
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if(response.isSuccessful()){
                    ItemResponse itemResponse = response.body();
                    if(itemResponse != null && itemResponse.isSuccess()){
                        populateSpinner(itemResponse.getData());
                    }else{
                        showToast("Error fetching items");
                    }
                } else {
                    showToast("Error fetching Items");
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                showToast("Network failure: " + t.getMessage());
            }
        });
    }
    private void populateSpinner(List<Item> itemList) {
        // Populate the spinner with category names
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_spinner_item, itemList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(itemList.get(position).getName());
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(itemList.get(position).getName());
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    private void updateTotalPrice(int quantity) {
        Item selectedItem = (Item) spinner.getSelectedItem();
        int itemPrice = selectedItem.getPrice();
        int totalPrice = quantity * itemPrice;
        textViewTotalPrice.setText("Total Harga: "+String.valueOf(totalPrice));
    }
    private void createTransaction() {
        // Retrieve data from UI elements
        int quantity = numberPickerQuantity.getValue();
        int itemId = ((Item) spinner.getSelectedItem()).getItemId();
        Item selectedItem = (Item) spinner.getSelectedItem();
        int itemPrice = selectedItem.getPrice();
        int totalPrice = quantity * itemPrice;
        Call<CreateTransactionResponse> call = apiService.createTransaction(itemId, quantity, totalPrice);
        call.enqueue(new Callback<CreateTransactionResponse>() {
            @Override
            public void onResponse(Call<CreateTransactionResponse> call, Response<CreateTransactionResponse> response) {
                if (response.isSuccessful()) {
                    showToast("Item created successfully");
                    finish();
                } else {
                    showToast("Error creating item");
                }
            }

            @Override
            public void onFailure(Call<CreateTransactionResponse> call, Throwable t) {
                showToast("Network failure: " + t.getMessage());
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
