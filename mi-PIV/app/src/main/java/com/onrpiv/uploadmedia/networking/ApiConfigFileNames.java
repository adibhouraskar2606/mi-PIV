package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 02/07/2019.
 */
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;


public interface ApiConfigFileNames {

    @Multipart
//    @POST("images/upload_image.php")
//    @POST("android_file_process.py")
    @POST("android_file_names_return.py")
    Call<ServerResponse> upload(
            @Header("Authorization") String authorization,
            @PartMap Map<String, RequestBody> map
    );
}