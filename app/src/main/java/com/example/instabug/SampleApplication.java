package com.example.instabug;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.instabug.apm.APM;
import com.instabug.bug.BugReporting;
import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.InstabugCustomTextPlaceHolder;
import com.instabug.library.internal.module.InstabugLocale;
import com.instabug.library.invocation.InstabugInvocationEvent;
import com.instabug.library.model.Report;
import com.instabug.library.ui.onboarding.WelcomeMessage;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;


public class SampleApplication extends Application {

    public static final String TAG = "SAMPLE_APP";

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

        //To show instabug debug logs if necessary
        Instabug.setDebugEnabled(true);

        //Settings custom strings to replace instabug's strings
        InstabugCustomTextPlaceHolder placeHolder = new InstabugCustomTextPlaceHolder();
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback");
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report");

        Instabug.setCustomTextPlaceHolders(placeHolder);

        //setting user attributes
        Instabug.setUserAttribute("USER_TYPE", "instabug user");

        Instabug.onReportSubmitHandler(new Report.OnReportCreatedListener() {
            @Override
            public void onReportCreated(Report report) {
                // perform blocking file generation operation
                Uri fileUri = generateLargeFile();
                // attach the file to the report after it had been generated completely
                report.addFileAttachment(fileUri, "attachmentFile.txt");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BugReporting.setAutoScreenRecordingEnabled(true);
        }

        APM.setEnabled(true);

    }

    /**
     * thread blocking file generation
     * @return generated file Uri
     */
    private Uri generateLargeFile() {
        Resources resources = getResources();
        File outputFile = new File(getExternalCacheDir()+"/attachmentFile.txt");
        InputStream resourceInputStream = resources.openRawResource(R.raw.extra);
        OutputStream fileOutputStream = null;
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try {
            outputFile.createNewFile();
            fileOutputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = resourceInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, readLength);
            }
        } catch (IOException ioException) {
            Log.e(TAG, "ioException", ioException);
        } finally {
            tryClosingClosable(resourceInputStream);
            tryClosingClosable(fileOutputStream);
        }
        return Uri.fromFile(outputFile);
    }

    private void tryClosingClosable(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ioException) {
                Log.e(TAG, "couldn't close closeable", ioException);
            }
        }
    }
}
