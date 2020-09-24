package com.example.fatmouf.Utilities;

import android.util.Log;

import com.example.fatmouf.BuildConfig;


public class MyLog {

    public static void LogE(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void LogD(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void LogI(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

}
