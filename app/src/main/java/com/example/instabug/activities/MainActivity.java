package com.example.instabug.activities;

import android.os.Bundle;

import com.example.instabug.R;
import com.instabug.wrapper.support.activity.InstabugActionBarActivity;

/**
 * @author mSobhy
 */
public class MainActivity extends InstabugActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}