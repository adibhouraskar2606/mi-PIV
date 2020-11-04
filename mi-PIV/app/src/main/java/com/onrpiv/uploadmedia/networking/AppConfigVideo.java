package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 03/21/2019.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppConfigVideo {

//    public static String BASE_URL = "http://144.39.217.90/";
    public static String BASE_URL = "http://144.39.217.90/cgi-enabled/";

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(AppConfigVideo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
