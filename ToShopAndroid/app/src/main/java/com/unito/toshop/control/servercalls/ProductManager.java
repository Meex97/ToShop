package com.unito.toshop.control.servercalls;


import android.util.Log;
import android.widget.Toast;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.control.API.ApiClient;
import com.unito.toshop.control.API.ApiInterface;
import com.unito.toshop.model.ProductClientResult;
import com.unito.toshop.model.ProductInfoResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductManager {

    private final static String TAG = ProductManager.class.getSimpleName();

    private static ProductManager productManager = null;

    public static ProductManager getInstance() {
        if (productManager == null) {
            productManager = new ProductManager();
        }
        return productManager;
    }

    public void getProductsListFH() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ProductInfoResult>> result = apiInterface.getFHProductList();
        result.enqueue(new Callback<List<ProductInfoResult>>() {
            @Override
            public void onResponse(Call<List<ProductInfoResult>> call, Response<List<ProductInfoResult>> response) {
                if (response.isSuccessful()){
                    List<ProductInfoResult> list = response.body();
                    Log.e(TAG, "response succesfull, list size: " + list.size());
                    Application.getInstance().getModel().putBean(Constants.PRODUCT_LIST_FH, list);
                } else {
                    Log.e(TAG, "response not succesfull");
                }
            }

            @Override
            public void onFailure(Call<List<ProductInfoResult>> call, Throwable t) {
                Log.e(TAG, "Error productListFH");
                t.printStackTrace();
                Toast.makeText(Application.getInstance().getCurrentActivity(), "Error occured", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getProductsListSH() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ProductClientResult>> result = apiInterface.getSHProductList();
        result.enqueue(new Callback<List<ProductClientResult>>() {
            @Override
            public void onResponse(Call<List<ProductClientResult>> call, Response<List<ProductClientResult>> response) {
                if (response.isSuccessful()){
                    List<ProductClientResult> list = response.body();
                    Log.e(TAG, "response succesfull, list size: " + list.size());
                    Application.getInstance().getModel().putBean(Constants.PRODUCT_LIST_SH, list);
                } else {
                    Log.e(TAG, "response not succesfull");
                }
            }

            @Override
            public void onFailure(Call<List<ProductClientResult>> call, Throwable t) {
                Log.e(TAG, "Error productListSH");
                t.printStackTrace();
                Toast.makeText(Application.getInstance().getCurrentActivity(), "Error occured", Toast.LENGTH_LONG).show();
            }
        });

    }


}
