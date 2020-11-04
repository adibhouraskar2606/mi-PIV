package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 10/29/2018.
 */
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppConfigVector {

    public static String BASE_URL = "http://144.39.217.90/cgi-enabled/";

    public static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(AppConfigVector.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
