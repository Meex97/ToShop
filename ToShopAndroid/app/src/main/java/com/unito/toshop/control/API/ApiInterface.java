package com.unito.toshop.control.API;

import com.unito.toshop.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiInterface {

    @POST("login?include=credential")
    Call<LoginResult> login(@Body LoginCredential credential);

    @GET("product/newProductList")
    Call<List<ProductInfoResult>> getFHProductList();

    @GET("product/secondhandProductList")
    Call<List<ProductClientResult>> getSHProductList();

    @GET("image/get/{imageName}")
    Call<ImageModel> getProductImage(@Path("imageName") String imageName);

}
