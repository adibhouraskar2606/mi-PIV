package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 10/29/2018.
 */
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface ApiConfigVectorFile {
    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrl(@Url String fileUrl);
}