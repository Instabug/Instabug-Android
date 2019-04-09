package com.example.instabug

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

import com.instabug.library.Instabug
import com.instabug.library.InstabugColorTheme
import com.instabug.library.InstabugCustomTextPlaceHolder
import com.instabug.library.internal.module.InstabugLocale
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.ui.onboarding.WelcomeMessage

import java.util.Locale


class SampleApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //initialing instabug
        Instabug.Builder(this, "48ad905e141bc665d064945f423aa414")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT,
                        InstabugInvocationEvent.FLOATING_BUTTON, InstabugInvocationEvent.TWO_FINGER_SWIPE_LEFT)
                .build()

        //Choosing instabug theme
        Instabug.setColorTheme(InstabugColorTheme.InstabugColorThemeLight)
        //Choosing type of attachments allowed
        //1. initial screenshot, 2. extra screenshot, 3. image from gallery, 4. voice note
        //5. screen record
        Instabug.setWelcomeMessageState(WelcomeMessage.State.LIVE)

        // TODO the following are 3 acceptable ways to force Locale in Instabug (last one is the only 1 applied)
        Instabug.setLocale(Locale(InstabugLocale.SIMPLIFIED_CHINESE.code,
                InstabugLocale.SIMPLIFIED_CHINESE.country))
        Instabug.setLocale(Locale(InstabugLocale.FRENCH.code))
        Instabug.setLocale(Locale.ENGLISH)

        //To show instabug debug logs if necessary
        Instabug.setDebugEnabled(true)

        //Settings custom strings to replace instabug's strings
        val placeHolder = InstabugCustomTextPlaceHolder()
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback")
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report")

        Instabug.setCustomTextPlaceHolders(placeHolder)

        //setting user attributes
        Instabug.setUserAttribute("USER_TYPE", "instabug user")

        Instabug.setAutoScreenRecordingEnabled(true)
    }
}
