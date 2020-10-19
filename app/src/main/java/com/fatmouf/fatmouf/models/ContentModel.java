package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContentModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    ArrayList<Content> data;

    public ArrayList<Content> getData() {
        return data;
    }
}
