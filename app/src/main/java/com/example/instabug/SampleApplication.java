package com.example.instabug;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.instabug.library.IBGColorTheme;
import com.instabug.library.IBGCustomTextPlaceHolder;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.InstabugCustomTextPlaceHolder;
import com.instabug.library.internal.module.InstabugLocale;
import com.instabug.library.invocation.InstabugInvocationEvent;

import java.security.KeyFactory;
import java.util.Locale;

/**
 * @author mSobhy
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        new Instabug.Builder(this, getString(R.string.INSTABUG_API_KEY))
                .setEmailFieldRequired(false)
                .setFloatingButtonOffsetFromTop(400)
                .setTheme(InstabugColorTheme.InstabugColorThemeLight)
                .setIntroMessageEnabled(false)
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .setAttachmentTypesEnabled(true, true, true, true, true)
                        // TODO the following are 3 acceptable ways to force Locale in Instabug (last one is the only 1 applied)
                .setLocale(new Locale(InstabugLocale.SIMPLIFIED_CHINESE.getCode(), InstabugLocale.SIMPLIFIED_CHINESE.getCountry()))
                .setLocale(new Locale(InstabugLocale.FRENCH.getCode()))
                .setLocale(Locale.GERMAN)
                .build();

        Instabug.setDebugEnabled(true);

        //Settings custom strings to replace instabug's strings
        InstabugCustomTextPlaceHolder placeHolder = new InstabugCustomTextPlaceHolder();
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback");
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report");

        Instabug.setCustomTextPlaceHolders(placeHolder);
    }
}
