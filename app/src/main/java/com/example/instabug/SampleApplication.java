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
import com.instabug.library.model.BugCategory;

import java.security.KeyFactory;
import java.util.ArrayList;
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

        ArrayList<BugCategory> bugCategories = new ArrayList<>();
        bugCategories.add(
                BugCategory.getInstance().withLabel("Map").withIcon(android.R.drawable.ic_dialog_map));
        bugCategories.add(BugCategory.getInstance()
                .withLabel("Alert")
                .withIcon(android.R.drawable.ic_dialog_alert));
        bugCategories.add(
                BugCategory.getInstance().withLabel("Mail").withIcon(android.R.drawable.ic_dialog_email));

        Instabug.setBugCategories(bugCategories);
    }
}
