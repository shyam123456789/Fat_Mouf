package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("status")
    @Expose
    boolean status;

    @SerializedName("message")
    @Expose
    String message;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
