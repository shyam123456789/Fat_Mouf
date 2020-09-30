package com.example.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserListModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    ArrayList<UserDetail> list;


    public ArrayList<UserDetail> getList() {
        return list;
    }
}
