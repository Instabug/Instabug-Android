package com.example.instabug;

import static com.instabug.library.settings.SettingsManager.VERBOSE;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.instabug.apm.APM;
import com.instabug.bug.BugReporting;
import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.InstabugCustomTextPlaceHolder;
import com.instabug.library.LogLevel;
import com.instabug.library.internal.module.InstabugLocale;
import com.instabug.library.invocation.InstabugInvocationEvent;
import com.instabug.library.ui.onboarding.WelcomeMessage;

import java.util.Locale;


public class SampleApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //initialing instabug
        new Instabug.Builder(this, "token")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT,
                        InstabugInvocationEvent.FLOATING_BUTTON, InstabugInvocationEvent.TWO_FINGER_SWIPE_LEFT)
                .setSdkDebugLogsLevel(LogLevel.VERBOSE) // <--- don't use this in production
                .build();

        //Choosing instabug theme
        Instabug.setColorTheme(InstabugColorTheme.InstabugColorThemeLight);
        //Choosing type of attachments allowed
        //1. initial screenshot, 2. extra screenshot, 3. image from gallery, 4. voice note
        //5. screen record
        Instabug.setWelcomeMessageState(WelcomeMessage.State.LIVE);

        // TODO the following are 3 acceptable ways to force Locale in Instabug (last one is the only 1 applied)
        Instabug.setLocale(new Locale(InstabugLocale.SIMPLIFIED_CHINESE.getCode(),
                InstabugLocale.SIMPLIFIED_CHINESE.getCountry()));
        Instabug.setLocale(new Locale(InstabugLocale.FRENCH.getCode()));
        Instabug.setLocale(Locale.ENGLISH);

        //Settings custom strings to replace instabug's strings
        InstabugCustomTextPlaceHolder placeHolder = new InstabugCustomTextPlaceHolder();
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback");
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report");

        Instabug.setCustomTextPlaceHolders(placeHolder);

        //setting user attributes
        Instabug.setUserAttribute("USER_TYPE", "instabug user");

        BugReporting.setAutoScreenRecordingEnabled(true);

        APM.setEnabled(true);

    }
}
