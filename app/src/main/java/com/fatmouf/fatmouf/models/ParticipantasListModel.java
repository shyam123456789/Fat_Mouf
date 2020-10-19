package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParticipantasListModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    public ArrayList<ParticipantasModel> data = null;


    public ArrayList<ParticipantasModel> getData() {
        return data;
    }
}
