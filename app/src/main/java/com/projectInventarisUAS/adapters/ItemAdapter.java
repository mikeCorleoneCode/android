    package com.projectInventarisUAS.adapters;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.projectInventarisUAS.R;
    import com.projectInventarisUAS.models.Item;

    import org.w3c.dom.Text;

    import java.util.List;

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

        private List<Item> itemList;
        private OnItemClickListener onItemClickListener;

        public interface OnItemClickListener {
            void onItemClick(Item item);
        }
        public ItemAdapter(List<Item> itemList, OnItemClickListener onItemClickListener) {
            this.itemList = itemList;
            this.onItemClickListener = onItemClickListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view, onItemClickListener, itemList);
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
            private TextView itemNameItemID;
            private TextView itemNameCategoryID;
            private TextView itemNameTextView;
            private TextView itemPriceTextView;
            private OnItemClickListener onItemClickListener;
            private List<Item> itemList;

            ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, List<Item> itemList) {
                super(itemView);
                this.onItemClickListener = onItemClickListener;
                this.itemList = itemList;
                itemNameItemID = itemView.findViewById(R.id.textViewItemID);
                itemNameCategoryID = itemView.findViewById(R.id.textViewCategoryID);
                itemNameTextView = itemView.findViewById(R.id.textView3);
                itemPriceTextView = itemView.findViewById(R.id.textView4);
                itemView.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(itemList.get(position));
                    }
                });
            }

            void bind(Item item) {
                // Bind data to views
                itemNameItemID.setText("Item ID: " + item.getItemId());
                itemNameCategoryID.setText("Kategori: " + item.getCategoryId());
                itemNameTextView.setText("Nama: " +item.getName());
                itemPriceTextView.setText("Harga: " + String.valueOf(item.getPrice()));
            }
        }
    }
