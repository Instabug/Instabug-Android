package com.example.instabug;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;

import com.instabug.library.InstabugTrackingDelegate;

/**
 * Created by vezikon on 10/15/16.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //To allow instabug to track user steps
        // and also add touches to screen recording
        InstabugTrackingDelegate.notifyActivityGotTouchEvent(ev, this);
        return super.dispatchTouchEvent(ev);
    }
}
