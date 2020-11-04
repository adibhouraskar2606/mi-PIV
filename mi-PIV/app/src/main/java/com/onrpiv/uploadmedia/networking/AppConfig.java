package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 10/29/2018.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppConfig {

//    public static String BASE_URL = "http://144.39.217.90/";
    public static String BASE_URL = "http://144.39.217.90/cgi-enabled/";

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
