package com.onrpiv.gui.networking;

/**
 * Created by sarbajit mukherjee on 11/06/2018.
 */
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;


public interface ApiConfigProcess {

    @Multipart
//    @POST("images/upload_image.php")
//    @POST("android_file_process.py")testWIDM.py
//    @POST("android_file_process_updatedOct29.py")
    @POST("testWIDM.py")
    Call<ServerResponseProcess> upload(
            @Header("Authorization") String authorization,
            @PartMap Map<String, RequestBody> map
    );
}