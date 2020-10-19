package com.fatmouf.fatmouf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePublicModel implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("challenge_type")
    @Expose
    public String challengeType;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("favour")
    @Expose
    public String favour;
    @SerializedName("media")
    @Expose
    public ArrayList<Medium> media = null;
    @SerializedName("userInfo")
    @Expose
    public ArrayList<UserDetail> userInfo = null;
    @SerializedName("is_accepted")
    @Expose
    public String isAccepted;

    protected HomePublicModel(Parcel in) {
        id = in.readString();
        challengeType = in.readString();
        title = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        createdDate = in.readString();
        updatedDate = in.readString();
        userId = in.readString();
        description = in.readString();
        favour = in.readString();
        media = in.createTypedArrayList(Medium.CREATOR);
        userInfo = in.createTypedArrayList(UserDetail.CREATOR);
        isAccepted = in.readString();
    }

    public static final Creator<HomePublicModel> CREATOR = new Creator<HomePublicModel>() {
        @Override
        public HomePublicModel createFromParcel(Parcel in) {
            return new HomePublicModel(in);
        }

        @Override
        public HomePublicModel[] newArray(int size) {
            return new HomePublicModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(challengeType);
        parcel.writeString(title);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        parcel.writeString(createdDate);
        parcel.writeString(updatedDate);
        parcel.writeString(userId);
        parcel.writeString(description);
        parcel.writeString(favour);
        parcel.writeTypedList(media);
        parcel.writeTypedList(userInfo);
        parcel.writeString(isAccepted);
    }


    public String getId() {
        return id;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getFavour() {
        return favour;
    }

    public ArrayList<Medium> getMedia() {
        return media;
    }

    public ArrayList<UserDetail> getUserInfo() {
        return userInfo;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public static Creator<HomePublicModel> getCREATOR() {
        return CREATOR;
    }
}
