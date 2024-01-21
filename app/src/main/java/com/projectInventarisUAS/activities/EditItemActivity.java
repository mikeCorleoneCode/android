package com.projectInventarisUAS.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projectInventarisUAS.R;
import com.projectInventarisUAS.models.Item;
import com.projectInventarisUAS.service.APIService;
import com.projectInventarisUAS.service.DeleteItemResponse;
import com.projectInventarisUAS.service.RetrofitInstance;
import com.projectInventarisUAS.service.UpdateItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditItemActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemPriceEditText;
    private Item originalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_edit);
        EditText itemIDEditText = findViewById(R.id.textInputID);
        itemNameEditText = findViewById(R.id.textInputNama);
        itemPriceEditText = findViewById(R.id.textInputHarga);
        Button saveChangesButton = findViewById(R.id.button);

        // Retrieve Item object from the intent
        originalItem = getIntent().getParcelableExtra("item");

        if (originalItem != null) {
            // Update UI with item details for editing
            itemIDEditText.setText(String.valueOf(originalItem.getItemId()));
            itemIDEditText.setFocusable(false);
            itemIDEditText.setClickable(false);
            itemNameEditText.setText(originalItem.getName());
            itemPriceEditText.setText(String.valueOf(originalItem.getPrice()));
        }
        saveChangesButton.setOnClickListener(view-> saveChangesAndFinish());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            // Handle delete action
            deleteItem();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void saveChangesAndFinish(){
        String updatedName = itemNameEditText.getText().toString().trim();
        int updatedPrice = Integer.parseInt(itemPriceEditText.getText().toString().trim());

        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

        Call<UpdateItemResponse> call = apiService.updateItem(originalItem.getItemId(), updatedName, updatedPrice);

        call.enqueue(new Callback<UpdateItemResponse>() {
            @Override
            public void onResponse(Call<UpdateItemResponse> call, Response<UpdateItemResponse> response) {
                if (response.isSuccessful()) {
                    UpdateItemResponse updateItemResponse = response.body();
                    if (updateItemResponse != null && !updateItemResponse.isError()) {
                        showToast("Item updated successfully");
                    } else {
                        showToast("Failed to update item");
                    }
                } else {
                    showToast("Failed to update item");
                }
                finish();
            }

            @Override
            public void onFailure(Call<UpdateItemResponse> call, Throwable t) {
            finish();
            }
        });
    }
    private void deleteItem() {
        APIService apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);
        Call<DeleteItemResponse> deleteCall = apiService.deleteItem(originalItem.getItemId());


        deleteCall.enqueue(new Callback<DeleteItemResponse>() {
            @Override
            public void onResponse(Call<DeleteItemResponse> call, Response<DeleteItemResponse> response) {
                if (response.isSuccessful()) {
                    DeleteItemResponse deleteItemResponse = response.body();
                    if (deleteItemResponse != null && !deleteItemResponse.isError()) {
                        // Deletion successful, show toast or handle accordingly
                        showToast("Item deleted successfully");
                    } else {
                        // Handle deletion error
                        showToast("Deletion failed");
                    }
                } else {
                    // Handle deletion error
                    showToast("Deletion failed");
                }
                finish(); // Finish the activity after update and/or delete
            }

            @Override
            public void onFailure(Call<DeleteItemResponse> call, Throwable t) {
                // Handle deletion failure
                showToast("Deletion failed");
                finish(); // Finish the activity even if deletion fails
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(EditItemActivity.this, message, Toast.LENGTH_SHORT).show();
    }
    // Additional methods for saving changes, validation, etc., can be added as needed
}
