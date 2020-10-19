package com.fatmouf.fatmouf.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupModel {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public Object description;
    @SerializedName("is_active")
    @Expose
    public String isActive;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("group_creator")
    @Expose
    public String groupCreator;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Object getDescription() {
        return description;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getImage() {
        return image;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
