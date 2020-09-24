package com.example.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePublicResponse extends ResponseModel {

    @SerializedName("data")
    @Expose
    public ArrayList<HomePublicModel> data = null;


    public ArrayList<HomePublicModel> getData() {
        return data;
    }
}
