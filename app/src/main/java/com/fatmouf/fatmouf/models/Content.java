package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("id")
    @Expose
    String id;


    @SerializedName("title")
    @Expose
    String title;


    @SerializedName("content")
    @Expose
    String content;


    @SerializedName("status")
    @Expose
    String status;


    @SerializedName("created_at")
    @Expose
    String created_at;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
