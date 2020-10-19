package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserModel extends ResponseModel{

    @SerializedName("data")
    @Expose
    UserDetail detail;

    public UserDetail getDetail() {
        return detail;
    }
}
