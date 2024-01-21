package com.projectInventarisUAS.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectInventarisUAS.R;
import com.projectInventarisUAS.models.Categories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Categories> categoryList;

    public CategoryAdapter(List<Categories> categoryList) {
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories categories = categoryList.get(position);
        holder.bind(categories);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoriesIdTextView;
        private TextView categoriesNameTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriesIdTextView = itemView.findViewById(R.id.textViewCategoryID);
            categoriesNameTextView = itemView.findViewById(R.id.textViewCategoryName);
        }

        void bind(Categories categories) {
            // Bind data to views
            categoriesIdTextView.setText("Nama: " +categories.getCategoryId());
            categoriesNameTextView.setText("Kategori: " + categories.getCategoryName());
        }
    }
}
