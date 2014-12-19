package com.zacharytamas.often;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by zjones on 12/18/14.
 */
public class OftenApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
    }
}
