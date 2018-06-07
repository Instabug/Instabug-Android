package com.example.instabug.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.instabug.BaseActivity;
import com.example.instabug.R;
import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.invocation.InstabugInvocationEvent;

import petrov.kristiyan.colorpicker.ColorPicker;

public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onShowInvocationEventsClicked(View view) {
        final CharSequence[] items = {"Shake", "Floating Button", "Screenshot", "Two Finger Swipe", "None"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Invocation Event");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Instabug.changeInvocationEvent(InstabugInvocationEvent.SHAKE);
                        break;
                    case 1:
                        Instabug.changeInvocationEvent(InstabugInvocationEvent.FLOATING_BUTTON);
                        break;
                    case 2:
                        Instabug.changeInvocationEvent(InstabugInvocationEvent.SCREENSHOT_GESTURE);
                        break;
                    case 3:
                        Instabug.changeInvocationEvent(InstabugInvocationEvent.TWO_FINGER_SWIPE_LEFT);
                        break;
                    case 4:
                        Instabug.changeInvocationEvent(InstabugInvocationEvent.NONE);
                        break;
                }
            }
        });
        builder.show();
    }

    public void onChangeThemeClicked(View view) {
        final CharSequence[] items = {"Light", "Dark"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Theme");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Instabug.setTheme(InstabugColorTheme.InstabugColorThemeLight);
                        break;
                    case 1:
                        Instabug.setTheme(InstabugColorTheme.InstabugColorThemeDark);
                        break;
                }
            }
        });
        builder.show();
    }

    public void onChangePrimaryColorClicked(View view) {
        ColorPicker colorPicker = new ColorPicker(this);
        colorPicker.show();
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                Instabug.disable();
                Instabug.setPrimaryColor(color);
                Instabug.enable();
            }

            @Override
            public void onCancel() {
                // put code
            }
        });

    }
}
