package com.fatmouf.fatmouf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("challenge_id")
    @Expose
    public String challengeId;
    @SerializedName("media_type")
    @Expose
    public String mediaType;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("video")
    @Expose
    public String video;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;


    protected Medium(Parcel in) {
        id = in.readString();
        challengeId = in.readString();
        mediaType = in.readString();
        image = in.readString();
        video = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Medium> CREATOR = new Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel in) {
            return new Medium(in);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(challengeId);
        parcel.writeString(mediaType);
        parcel.writeString(image);
        parcel.writeString(video);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }

    public String getId() {
        return id;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public static Creator<Medium> getCREATOR() {
        return CREATOR;
    }
}
