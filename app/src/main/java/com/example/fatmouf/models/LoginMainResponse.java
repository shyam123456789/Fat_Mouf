package com.example.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginMainResponse {

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("user")
    @Expose
    UserDetail user;

    public String getToken() {
        return token;
    }

    public UserDetail getUser() {
        return user;
    }

}
