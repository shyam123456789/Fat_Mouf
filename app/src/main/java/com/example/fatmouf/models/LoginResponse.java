package com.example.fatmouf.models;

import com.example.fatmouf.activities.Login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ResponseModel{

    @SerializedName("data")
    @Expose
    LoginMainResponse data;

    public LoginMainResponse getData() {
        return data;
    }
}
