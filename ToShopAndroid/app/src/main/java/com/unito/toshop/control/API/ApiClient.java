package com.unito.toshop.control.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiClient {

//    private static final String URL = "http://localhost:8080/api/";
//    private static final String URL = "http://10.0.2.2:8080/api/"; //we have to use this link cause of problems with localhost and emulator (it's the equivalent for localhost)
    private static final String URL = "https://toshoppone.herokuapp.com/api/"; //heroku
    private static Retrofit retrofit = null;
    public static Gson gson = new GsonBuilder().create();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(getHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Accept","application/json").build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return okHttpClient;
    }

}
