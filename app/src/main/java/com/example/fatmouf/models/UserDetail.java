package com.example.fatmouf.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetail implements Parcelable {


    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("phonenumber")
    @Expose
    public String phonenumber;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("google_id")
    @Expose
    public Object googleId;
    @SerializedName("facebook_id")
    @Expose
    public Object facebookId;
    @SerializedName("apple_id")
    @Expose
    public Object appleId;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("wallet_balance")
    @Expose
    public String walletBalance;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("device_type")
    @Expose
    public String deviceType;
    @SerializedName("verified")
    @Expose
    public String verified;
    @SerializedName("is_delete")
    @Expose
    public String isDelete;
    @SerializedName("is_delete_date")
    @Expose
    public String isDeleteDate;
    @SerializedName("is_active")
    @Expose
    public String isActive;
    @SerializedName("is_activate_date")
    @Expose
    public String isActivateDate;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("last_login")
    @Expose
    public Object lastLogin;
    @SerializedName("admin")
    @Expose
    public String admin;
    @SerializedName("zip_code")
    @Expose
    public String zipCode;
    @SerializedName("device_id")
    @Expose
    public String deviceId;
    @SerializedName("phone_verified")
    @Expose
    public String phoneVerified;

    @SerializedName("token")
    @Expose
    public String token;

    public boolean selected;


    protected UserDetail(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        phonenumber = in.readString();
        email = in.readString();
        walletBalance = in.readString();
        address = in.readString();
        lat = in.readString();
        lng = in.readString();
        city = in.readString();
        state = in.readString();
        deviceType = in.readString();
        verified = in.readString();
        isDelete = in.readString();
        isDeleteDate = in.readString();
        isActive = in.readString();
        isActivateDate = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        admin = in.readString();
        zipCode = in.readString();
        deviceId = in.readString();
        phoneVerified = in.readString();
        token = in.readString();
        selected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phonenumber);
        dest.writeString(email);
        dest.writeString(walletBalance);
        dest.writeString(address);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(deviceType);
        dest.writeString(verified);
        dest.writeString(isDelete);
        dest.writeString(isDeleteDate);
        dest.writeString(isActive);
        dest.writeString(isActivateDate);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(admin);
        dest.writeString(zipCode);
        dest.writeString(deviceId);
        dest.writeString(phoneVerified);
        dest.writeString(token);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserDetail> CREATOR = new Creator<UserDetail>() {
        @Override
        public UserDetail createFromParcel(Parcel in) {
            return new UserDetail(in);
        }

        @Override
        public UserDetail[] newArray(int size) {
            return new UserDetail[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getImage() {
        return image;
    }

    public Object getGoogleId() {
        return googleId;
    }

    public Object getFacebookId() {
        return facebookId;
    }

    public Object getAppleId() {
        return appleId;
    }

    public String getEmail() {
        return email;
    }

    public String getWalletBalance() {
        return walletBalance;
    }

    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public String getVerified() {
        return verified;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public String getIsDeleteDate() {
        return isDeleteDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getIsActivateDate() {
        return isActivateDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public String getAdmin() {
        return admin;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getPhoneVerified() {
        return phoneVerified;
    }

    public String getToken() {
        return token;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
