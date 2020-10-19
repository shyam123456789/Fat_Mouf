package com.fatmouf.fatmouf.models;

import com.fatmouf.fatmouf.models.AddActivityModel2;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddActivityModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    AddActivityModel2 data;

    public AddActivityModel2 getData() {
        return data;
    }
}
