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
        private TextView transactionIDTextView;
        private TextView itemIDTextView;
        private TextView quantityTextView;
        private TextView totalPriceTextView;
        private TextView timestampTextView;

        ViewHolder(@NonNull View transactionView) {
            super(transactionView);
            transactionIDTextView = transactionView.findViewById(R.id.textViewTransactionID);
            itemIDTextView = transactionView.findViewById(R.id.textViewItemID);
            quantityTextView = transactionView.findViewById(R.id.textViewQuantity);
            totalPriceTextView = transactionView.findViewById(R.id.textViewTotalPrice);
            timestampTextView = transactionView.findViewById(R.id.textViewTimestamp);

        }

        void bind(Transaction transaction) {
            // Bind data to views
            transactionIDTextView.setText("TransactionID: " + String.valueOf(transaction.getTransactionId()));
            itemIDTextView.setText("ItemID: "+ String.valueOf(transaction.getItemId()));
            quantityTextView.setText("Quantity: " + String.valueOf(transaction.getQuantity())+ " Item");
            totalPriceTextView.setText("Total Price: Rp " + String.valueOf(transaction.getTotalPrice()));
            timestampTextView.setText("History: " +transaction.getTimestamp());
        }
    }
    }

