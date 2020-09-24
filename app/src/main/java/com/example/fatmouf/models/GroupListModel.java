package com.example.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupListModel extends ResponseModel{

    @SerializedName("data")
    @Expose
    ArrayList<GroupModel> list;


    public ArrayList<GroupModel> getList() {
        return list;
    }
}
