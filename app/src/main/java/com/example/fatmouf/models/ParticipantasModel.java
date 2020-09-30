package com.example.fatmouf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ParticipantasModel implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("challenge_id")
    @Expose
    public String challengeId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("is_accepted")
    @Expose
    public String isAccepted;
    @SerializedName("is_winner")
    @Expose
    public String isWinner;
    @SerializedName("is_reported")
    @Expose
    public String isReported;
    @SerializedName("reported_user")
    @Expose
    public String reportedUser;
    @SerializedName("report_reason")
    @Expose
    public Object reportReason;
    @SerializedName("created_date")
    @Expose
    public String createdDate;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("userInfo")
    @Expose
    public ArrayList<UserDetail> userInfo = null;

    protected ParticipantasModel(Parcel in) {
        id = in.readString();
        challengeId = in.readString();
        userId = in.readString();
        isAccepted = in.readString();
        isWinner = in.readString();
        isReported = in.readString();
        reportedUser = in.readString();
        createdDate = in.readString();
        updatedDate = in.readString();
        userInfo = in.createTypedArrayList(UserDetail.CREATOR);
    }

    public static final Creator<ParticipantasModel> CREATOR = new Creator<ParticipantasModel>() {
        @Override
        public ParticipantasModel createFromParcel(Parcel in) {
            return new ParticipantasModel(in);
        }

        @Override
        public ParticipantasModel[] newArray(int size) {
            return new ParticipantasModel[size];
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
        parcel.writeString(userId);
        parcel.writeString(isAccepted);
        parcel.writeString(isWinner);
        parcel.writeString(isReported);
        parcel.writeString(reportedUser);
        parcel.writeString(createdDate);
        parcel.writeString(updatedDate);
        parcel.writeTypedList(userInfo);
    }


    public String getId() {
        return id;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public String getIsWinner() {
        return isWinner;
    }

    public String getIsReported() {
        return isReported;
    }

    public String getReportedUser() {
        return reportedUser;
    }

    public Object getReportReason() {
        return reportReason;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public ArrayList<UserDetail> getUserInfo() {
        return userInfo;
    }

    public static Creator<ParticipantasModel> getCREATOR() {
        return CREATOR;
    }
}
