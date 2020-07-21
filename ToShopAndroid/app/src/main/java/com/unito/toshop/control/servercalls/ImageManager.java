package com.unito.toshop.control.servercalls;

import android.util.Log;
import android.widget.Toast;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.control.API.ApiClient;
import com.unito.toshop.control.API.ApiInterface;
import com.unito.toshop.model.ImageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageManager {

    private final static String TAG = ImageManager.class.getSimpleName();

    private static ImageManager imageManager = null;

    public static ImageManager getInstance() {
        if (imageManager == null) {
            imageManager = new ImageManager();
        }
        return imageManager;
    }

    public ImageModel getImage(String path) {
        Log.e(TAG, "Image path: " + path);
        ImageModel image = null;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        final Call<ImageModel> result = apiInterface.getProductImage(path);
        result.enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if (response.isSuccessful()) {
                    ImageModel imageModel = response.body();
                    Application.getInstance().getModel().putBean(Constants.PRODUCT_IMAGE, imageModel);
                    Log.e(TAG, "Response successful, response: " + response.body());
                } else {
                    Log.e(TAG, "Response not successful, response: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                Log.e(TAG, "Fail");
                t.printStackTrace();
            }
        });
        image = (ImageModel) Application.getInstance().getModel().getBean(Constants.PRODUCT_IMAGE);
        return image;
    }

}
