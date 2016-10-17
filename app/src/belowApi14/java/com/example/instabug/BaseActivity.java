package com.example.instabug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.instabug.library.InstabugTrackingDelegate;


/**
 * For below API 14 support you need to manually track
 * the lifecycle of the activity
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InstabugTrackingDelegate.notifyActivityCreated(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        InstabugTrackingDelegate.notifyActivityStarted(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        InstabugTrackingDelegate.notifyActivityResumed(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        InstabugTrackingDelegate.notifyActivityPaused(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        InstabugTrackingDelegate.notifyActivityCreated(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InstabugTrackingDelegate.notifyActivityDestroyed(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //To allow instabug to track user steps
        // and also add touches to screen recording
        InstabugTrackingDelegate.notifyActivityGotTouchEvent(ev, this);
        return super.dispatchTouchEvent(ev);
    }
}
