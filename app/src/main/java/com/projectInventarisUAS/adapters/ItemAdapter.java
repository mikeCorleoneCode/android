    package com.projectInventarisUAS.adapters;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.projectInventarisUAS.R;
    import com.projectInventarisUAS.models.Item;

    import java.util.List;

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

        private List<Item> itemList;

        public ItemAdapter(List<Item> itemList) {
            this.itemList = itemList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView itemNameTextView;
            private TextView itemPriceTextView;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemNameTextView = itemView.findViewById(R.id.textView3);
                itemPriceTextView = itemView.findViewById(R.id.textView4);
            }

            void bind(Item item) {
                // Bind data to views
                itemNameTextView.setText("Nama: " +item.getName());
                itemPriceTextView.setText("Harga: " + String.valueOf(item.getPrice()));
            }
        }
    }
