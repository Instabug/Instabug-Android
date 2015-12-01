package com.example.instabug.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.instabug.R;
import com.instabug.library.Instabug;

public class SettingsActivity extends ActionBarActivity {
    private EditText commentHint, defaultEmail, emailHint, bugReportMessage, feedbackMessage, emailRequiredMessage, commentRequiredMessage;
    private CheckBox emailRequired, emailEnabled, commentRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_settings);
        emailEnabled = (CheckBox) findViewById(R.id.email_enabled);
        emailRequired = (CheckBox) findViewById(R.id.email_required);
        commentRequired = (CheckBox) findViewById(R.id.comment_required);
        emailRequiredMessage = (EditText) findViewById(R.id.invalid_email_message);
        commentRequiredMessage = (EditText) findViewById(R.id.invalid_comment_message);
        commentHint = (EditText) findViewById(R.id.comment_field_hint);
        defaultEmail = (EditText) findViewById(R.id.default_email);
        emailHint = (EditText) findViewById(R.id.email_field_hint);
        bugReportMessage = (EditText) findViewById(R.id.post_bug_report_message);
        feedbackMessage = (EditText) findViewById(R.id.post_feedback_report_message);
        emailEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Instabug.getInstance().enableEmailField(b, emailRequired.isChecked());
            }
        });

        emailRequired.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Instabug.getInstance().enableEmailField(true, b);
            }
        });

        commentRequired.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Instabug.getInstance().setCommentRequired(b);
            }
        });

        commentRequiredMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setCommentRequired(true, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        emailRequiredMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setInvalidEmailMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        feedbackMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setPostFeedbackMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bugReportMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setPostBugReportMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        commentHint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setCommentFieldHint(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        defaultEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setDefaultEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        emailHint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!charSequence.toString().isEmpty())
                    Instabug.getInstance().setEmailFieldHint(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
