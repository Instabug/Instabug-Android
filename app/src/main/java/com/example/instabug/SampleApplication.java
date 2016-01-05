package com.example.instabug;

import android.app.Application;

import com.instabug.library.IBGColorTheme;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

/**
 * @author mSobhy
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Instabug.Builder(this, "f501f761142981d54b1fdea93963a934")
                .setDebugEnabled(true)
                .setEmailFieldRequired(false)
                .setColorTheme(IBGColorTheme.IBGColorThemeLight)
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventFloatingButton)
                .setFloatingButtonOffsetFromTop(400)
                .build();
    }
}
