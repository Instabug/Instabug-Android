package com.example.instabug;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.InstabugCustomTextPlaceHolder;
import com.instabug.library.bugreporting.model.ReportCategory;
import com.instabug.library.internal.module.InstabugLocale;
import com.instabug.library.invocation.InstabugInvocationEvent;

import java.util.ArrayList;
import java.util.Locale;


public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        //initialing instabug
        new Instabug.Builder(this, "f501f761142981d54b1fdea93963a934")
                .setInvocationEvent(InstabugInvocationEvent.SHAKE)
                .build();

        //adding some customizations
        Instabug.setEmailFieldRequired(false);
        //Choosing instabug theme
        Instabug.setTheme(InstabugColorTheme.InstabugColorThemeLight);
        //Choosing type of attachments allowed
        //1. initial screenshot, 2. extra screenshot, 3. image from gallery, 4. voice note
        //5. screen record
        Instabug.setAttachmentTypesEnabled(true, true, true, true, true);
        //For setting instabug bug intro message enabled or disabled
        Instabug.setIntroMessageEnabled(false);

        // TODO the following are 3 acceptable ways to force Locale in Instabug (last one is the only 1 applied)
        Instabug.setLocale(new Locale(InstabugLocale.SIMPLIFIED_CHINESE.getCode(),
                InstabugLocale.SIMPLIFIED_CHINESE.getCountry()));
        Instabug.setLocale(new Locale(InstabugLocale.FRENCH.getCode()));
        Instabug.setLocale(Locale.GERMAN);

        //To show instabug debug logs if necessary
        Instabug.setDebugEnabled(true);

        //Settings custom strings to replace instabug's strings
        InstabugCustomTextPlaceHolder placeHolder = new InstabugCustomTextPlaceHolder();
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback");
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report");

        Instabug.setCustomTextPlaceHolders(placeHolder);

        //Setting report categories
        ArrayList<ReportCategory> reportCategories = new ArrayList<>();
        reportCategories.add(
                ReportCategory.getInstance().withLabel("Map").withIcon(android.R.drawable
                        .ic_dialog_map));
        reportCategories.add(ReportCategory.getInstance()
                .withLabel("Alert")
                .withIcon(android.R.drawable.ic_dialog_alert));
        reportCategories.add(
                ReportCategory.getInstance().withLabel("Mail").withIcon(android.R.drawable
                        .ic_dialog_email));

        Instabug.setReportCategories(reportCategories);

        //setting user attributes
        Instabug.setUserAttribute("USER_TYPE", "instabug user");

        Instabug.setAutoScreenRecordingEnabled(true);
    }
}
