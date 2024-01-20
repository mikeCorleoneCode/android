package com.projectInventarisUAS.service;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("api.php?apicall=itemview")
    Call<ItemResponse> getListItem();
    @GET("api.php?apicall=categoryview")
    Call<CategoryResponse> getListCategories();
    @GET("api.php?apicall=transactionview")
    Call<TransactionResponse> getListTransaction();
    @FormUrlEncoded
    @POST("api.php?apicall=itemcreate")
    Call<CreateItemResponse> createItem(
            @Field("categoryId") int categoryId,
            @Field("name") String name,
            @Field("imagePath") String imagePath,
            @Field("price") int price);
    @FormUrlEncoded
    @POST("api.php?apicall=categorycreate")
    Call<CreateCategoryResponse> createCategory(
            @Field("categoryname") String categoryName);
    @FormUrlEncoded
    @POST("api.php?apicall=transactioncreate")
    Call<CreateTransactionResponse> createTransaction(
            @Field("itemId") int itemId,
            @Field("quantity") int quantity,
            @Field("totalPrice") int totalPrice);
}
