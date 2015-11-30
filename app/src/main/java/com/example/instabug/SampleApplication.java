package com.example.instabug;

import android.app.Application;

import com.instabug.library.Instabug;

/**
 * @author mSobhy
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Instabug
                .initialize(this, "cbeaee267486966ed22499bd554f8924")
                .setInvocationEvent(Instabug.IBGInvocationEvent.IBGInvocationEventShake)
                .setEmailIsRequired(false);
    }
}
