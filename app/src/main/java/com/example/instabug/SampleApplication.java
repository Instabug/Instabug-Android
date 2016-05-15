package com.example.instabug;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.instabug.library.IBGColorTheme;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;
import com.instabug.library.internal.module.InstabugLocale;

import java.util.Locale;

/**
 * @author mSobhy
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        new Instabug.Builder(this, "f501f761142981d54b1fdea93963a934")
                .setDebugEnabled(true)
                .setEmailFieldRequired(false)
                .setFloatingButtonOffsetFromTop(400)
                .setColorTheme(IBGColorTheme.IBGColorThemeLight)
                .setShouldShowIntroDialog(false)
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                        // TODO the following are 3 acceptable ways to force Locale in Instabug (last one is the only 1 applied)
                .setLocale(new Locale(InstabugLocale.SIMPLIFIED_CHINESE.getCode(), InstabugLocale.SIMPLIFIED_CHINESE.getCountry()))
                .setLocale(new Locale(InstabugLocale.FRENCH.getCode()))
                .setLocale(Locale.GERMAN)
                .build();
    }
}
