package com.example.fatmouf.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.service.autofill.UserData;

import com.example.fatmouf.models.UserDetail;
import com.google.gson.Gson;

public final class PreferenceManger {
    public static PreferenceManger preferenceManger;
    public static SharedPreferences sharedPreferences;

    public static PreferenceManger getPreferenceManger() {
        return preferenceManger;
    }

    public static void initPreference(Context context) {
        if (preferenceManger == null) {
            sharedPreferences = context.getSharedPreferences("NCApp", Context.MODE_PRIVATE);
            preferenceManger = new PreferenceManger();
        }
    }

    public void setAuthToken(String token) {
        sharedPreferences.edit().putString("auth", token).apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString("auth", null);
    }

    public void setUserdetail(UserDetail value) {
        sharedPreferences.edit().putString("userdetail", new Gson().toJson(value)).apply();
    }

    public UserDetail getUserdetail() {
        UserDetail userdetail = null;
        if (sharedPreferences.getString("userdetail", null) != null) {
            userdetail = new Gson().fromJson(sharedPreferences.getString("userdetail", null), UserDetail.class);
        }
        return userdetail;
    }

    public int getAppIntro() {
        return sharedPreferences.getInt("AppIntro", 0);
    }

    public void setAppIntro() {
        sharedPreferences.edit().putInt("AppIntro", 1912).apply();
        sharedPreferences.edit().putInt("AppIntro", 1912).apply();
    }


    public void LogOut() {
        sharedPreferences.edit().clear().apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void setBoolean(String key, boolean b) {
        sharedPreferences.edit().putBoolean(key, b).commit();
    }


    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void resetPreference() {
        sharedPreferences.edit().clear().commit();
    }

    public void clearSession() {
        sharedPreferences.edit().clear().commit();
    }


}
