package com.onrpiv.gui.networking;

/**
 * Created by sarbajit mukherjee on 10/29/2018.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface ApiConfigVectorFile {
    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrl(@Url String fileUrl);
}