package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddActivityModel2 {

    @SerializedName("challenge_id")
    @Expose
    String challenge_id;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("user_id")
    @Expose
    String user_id;

    @SerializedName("activity_id")
    @Expose
    String activity_id;


    public String getChallenge_id() {
        return challenge_id;
    }

    public String getDescription() {
        return description;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getActivity_id() {
        return activity_id;
    }
}
