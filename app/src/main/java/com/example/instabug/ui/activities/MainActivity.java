package com.example.instabug.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.instabug.BaseActivity;
import com.example.instabug.R;
import com.example.instabug.fcm.RegistrationIntentService;
import com.instabug.bug.BugReporting;
import com.instabug.featuresrequest.FeatureRequests;
import com.instabug.library.Instabug;
import com.instabug.library.logging.InstabugLog;
import com.instabug.library.logging.InstabugNetworkLog;
import com.instabug.library.ui.onboarding.WelcomeMessage;
import com.instabug.survey.Surveys;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instabug logs
        InstabugLog.d("MainActivity - Created");

        registerFCM();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        doNetworkRequest();
    }

    private void registerFCM() {
        // Start IntentService to register this application with FCM.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

    public void onShowLiveOnboardingMessageClicked(View view) {
        Instabug.showWelcomeMessage(WelcomeMessage.State.LIVE);
    }

    public void onShowBetaOnboardingMessageClicked(View view) {
        Instabug.showWelcomeMessage(WelcomeMessage.State.BETA);
    }

    public void onShowInstabugClicked(View view) {
        BugReporting.show(BugReporting.ReportType.BUG);
    }

    public void onCrashTheAppClicked(View view) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Are you sure that want to crash the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int[] arrayOfIntegers = new int[]{0, 1, 2, 3};
                        int i = arrayOfIntegers[5];
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void showNPSSurvey(View view) {
        Surveys.showSurvey("ulUaFocMCejDr3Ldd8VBaA");
    }

    public void showMultipleQuestionSurvey(View view) {
        Surveys.showSurvey("AGI5OH47k3eEAIKj-yKDWA");
    }

    public void showFeatureRequests(View view) {
        FeatureRequests.show();
    }

    public void onShowSettingsClicked(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void doNetworkRequest() {
        new FetchMoviesData().execute();
    }

    public void onHeaderImageClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instabug.com"));
        startActivity(browserIntent);
    }

    private class FetchMoviesData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String moviesJsonStr = null;

            try {

                URL url = new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=a491e06af68296a1fad86c70235e98f9 ");
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("ID", "25");
                    jsonParam.put("description", "Real");
                    jsonParam.put("enable", "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonParam.toString());
                out.close();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;

                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonStr = buffer.toString();

                //logging network request to instabug
                InstabugNetworkLog networkLog = new InstabugNetworkLog();
                networkLog.log(urlConnection, jsonParam.toString(), moviesJsonStr);

                return moviesJsonStr;
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Response", s + "");
        }
    }

}
