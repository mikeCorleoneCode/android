    package com.projectInventarisUAS.activities;

    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.projectInventarisUAS.R;
    import com.projectInventarisUAS.models.Categories;
    import com.projectInventarisUAS.service.APIService;
    import com.projectInventarisUAS.service.CategoryResponse;
    import com.projectInventarisUAS.service.CreateItemResponse;
    import com.projectInventarisUAS.service.RetrofitInstance;

    import java.util.List;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class CreateItemActivity extends AppCompatActivity {
        private Spinner spinner;
        private EditText editTextNama;
        private EditText editTextHarga;

        private APIService apiService;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.item_add);

            // Initialize UI components
            spinner = findViewById(R.id.spinner);
            editTextNama = findViewById(R.id.textInputNama);
            editTextHarga = findViewById(R.id.textInputHarga);
            Button submitButton = findViewById(R.id.button);

            // Initialize API service
            apiService = RetrofitInstance.getRetrofitInstance().create(APIService.class);

            // Fetch categories for the spinner
            fetchCategories();

            // Set onClickListener for the submit button
            submitButton.setOnClickListener(view -> createItem());
        }

        private void fetchCategories() {
            Call<CategoryResponse> call = apiService.getListCategories();
            call.enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                    if (response.isSuccessful()) {
                        CategoryResponse categoryResponse = response.body();
                        if (categoryResponse != null && categoryResponse.isSuccess()) {
                            populateSpinner(categoryResponse.getData());
                        } else {
                            // Handle error in fetching categories
                            showToast("Error fetching categories");
                        }
                    } else {
                        // Handle error in fetching categories
                        showToast("Error fetching categories");
                    }
                }

                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                    // Handle network failure
                    showToast("Network failure: " + t.getMessage());
                }
            });
        }

        private void populateSpinner(List<Categories> categoriesList) {
            // Populate the spinner with category names
            ArrayAdapter<Categories> adapter = new ArrayAdapter<Categories>(this,
                    android.R.layout.simple_spinner_item, categoriesList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setText(categoriesList.get(position).getCategoryName());
                    return view;
                }

                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setText(categoriesList.get(position).getCategoryName());
                    return view;
                }
            };
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        }

        private void createItem() {
            // Retrieve data from UI elements
            String nama = editTextNama.getText().toString().trim();
            String harga = editTextHarga.getText().toString().trim();
            int categoryId = ((Categories) spinner.getSelectedItem()).getCategoryId();

            Call<CreateItemResponse> call = apiService.createItem(categoryId, nama, "path/test", Integer.parseInt(harga));
            call.enqueue(new Callback<CreateItemResponse>() {
                @Override
                public void onResponse(Call<CreateItemResponse> call, Response<CreateItemResponse> response) {
                    if (response.isSuccessful()) {
                        showToast("Item created successfully");
                        finish();
                    } else {
                        showToast("Error creating item");
                    }
                }

                @Override
                public void onFailure(Call<CreateItemResponse> call, Throwable t) {
                    showToast("Network failure: " + t.getMessage());
                }
            });
        }
        private void showToast(String message) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }
