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
                .initialize(this, "f501f761142981d54b1fdea93963a934")
                .setInvocationEvent(Instabug.IBGInvocationEvent.IBGInvocationEventShake)
                .setEmailIsRequired(false);
    }
}
