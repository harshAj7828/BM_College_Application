package com.example.bmcollege;

import android.app.Application;

import com.onesignal.OneSignal;

public class OneSingnal extends Application {
    private static final String ONESIGNAL_APP_ID = "204fc9e7-c1c8-41cd-a328-9fa022e9fedb";
    @Override
    public void onCreate() {
        super.onCreate();


        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
         
    }
}
