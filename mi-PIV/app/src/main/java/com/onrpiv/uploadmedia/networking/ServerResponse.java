package com.onrpiv.uploadmedia.networking;

/**
 * Created by sarbajit mukherjee on 10/29/2018.
 */

import com.google.gson.annotations.SerializedName;


public class ServerResponse {

    // variable name should be same as in the json response from php

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

}