package com.unito.toshop.control.servercalls;

import android.util.Log;
import android.widget.Toast;
import com.unito.toshop.Application;
import com.unito.toshop.Constants;
import com.unito.toshop.control.API.ApiClient;
import com.unito.toshop.control.API.ApiInterface;
import com.unito.toshop.model.LoginCredential;
import com.unito.toshop.model.LoginResult;
import com.unito.toshop.view.activity.ActivityLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {

    private final static String TAG = LoginManager.class.getSimpleName();

    private static LoginManager loginManager = null;

    public static LoginManager getInstance() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public void login(LoginCredential loginCredential) {
        Log.e(TAG,"email da loginCredential: " + loginCredential.getUsername());
        Log.e(TAG,"password da loginCredential: " + loginCredential.getPassword());
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResult> result = apiInterface.login(loginCredential);
        result.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    LoginResult loginResult = response.body();
                    Log.e(TAG, "response successful, response: " + response.body());
                    Application.getInstance().getPersistentModel().saveBean(Constants.LOGIN_RESULT, loginResult);
                    try {
                        ActivityLogin activityLogin = (ActivityLogin) Application.getInstance().getCurrentActivity();
                        activityLogin.showActivityHome();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(Application.getInstance().getCurrentActivity(),"Logged in!", Toast.LENGTH_LONG).show();
                } else {
                    Log.e(TAG, "response not successful, response: " + response.body());
                    Toast.makeText(Application.getInstance().getCurrentActivity(),"Login error, please check again your credentials or your internet connection", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e(TAG, "Fail: " + t.getMessage());
                Toast.makeText(Application.getInstance().getCurrentActivity(),"Login error, please check again your credentials or your internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

}
