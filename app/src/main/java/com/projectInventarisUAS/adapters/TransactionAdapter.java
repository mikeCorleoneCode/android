package com.projectInventarisUAS.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectInventarisUAS.R;
import com.projectInventarisUAS.models.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactionList;

    public TransactionAdapter(List<Transaction> transactionList){
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNameTextView;
        private TextView itemPriceTextView;

        ViewHolder(@NonNull View transactionView) {
            super(transactionView);
            itemNameTextView = transactionView.findViewById(R.id.textView3);
            itemPriceTextView = transactionView.findViewById(R.id.textView4);
        }

        void bind(Transaction transaction) {
            // Bind data to views
            itemNameTextView.setText("Quantity " +String.valueOf(transaction.getQuantity()));
            itemPriceTextView.setText("TotalPrice : " + String.valueOf(transaction.getTotalPrice()));
        }
    }
    }

