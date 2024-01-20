package com.projectInventarisUAS.service;

import com.projectInventarisUAS.models.Item;

import java.util.List;

public class ItemResponse {
        private boolean success;
        private List<Item> data;

        public boolean isSuccess() {
            return success;
        }

        public List<Item> getData() {
            return data;
        }


}
