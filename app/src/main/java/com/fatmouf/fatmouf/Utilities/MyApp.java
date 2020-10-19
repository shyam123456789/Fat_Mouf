package com.fatmouf.fatmouf.Utilities;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {

    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManger.initPreference(this);

        app = this;
    }


    public static Context getAppContext() {
        return app;
    }

}
