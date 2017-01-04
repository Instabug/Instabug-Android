package com.example.instabug.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.instabug.BaseActivity;
import com.example.instabug.R;
import com.example.instabug.gcm.RegistrationIntentService;
import com.instabug.library.Instabug;
import com.instabug.library.invocation.InstabugInvocationMode;
import com.instabug.library.logging.InstabugLog;
import com.instabug.library.logging.InstabugNetworkLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.spinner)
    Spinner spinner;
    ImageView headerImage;

    private static String[] colorNames = {"Red", "Blue", "Green", "Yellow"};
    private static int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Instabug logs
        InstabugLog.d("MainActivity - Created");

        registerGCM();

        headerImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.headerImageView);
        headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHeaderImageClicked();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(navigationView.getMenu().getItem(0).getItemId());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Instabug.setPrimaryColor(colors[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void registerGCM() {
        // Start IntentService to register this application with GCM.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

    @OnClick(R.id.feedback_fab)
    public void onFeedbackFABClicked() {
        Instabug.invoke();
    }

    @OnClick(R.id.show_intro_message)
    public void onShowIntroMessageClicked() {
        Instabug.showIntroMessage();
    }

    @OnClick(R.id.start_feedback)
    public void onFeedbackClicked() {
        Instabug.invoke(InstabugInvocationMode.NEW_FEEDBACK);
    }

    @OnClick(R.id.start_bug_report)
    public void onBugReportClicked() {
        Instabug.invoke(InstabugInvocationMode.NEW_FEEDBACK);
    }

    @OnClick(R.id.start_new_conversation)
    public void onNewConversationClicked() {
        Instabug.invoke(InstabugInvocationMode.NEW_CHAT);
    }

    @OnClick(R.id.start_conversation_list)
    public void onConversationListClicked() {
        Instabug.invoke(InstabugInvocationMode.CHATS_LIST);
    }

    @OnClick(R.id.show_new_messages_count)
    public void onNewMessageCountClicked() {
        Toast.makeText(this,
                "Number of unread messages: " +
                        String.valueOf(Instabug.getUnreadMessagesCount()), Toast.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.do_network_request)
    public void onDoNetworkRequestClicked(){
        new FetchMoviesData().execute();
    }

    public void onHeaderImageClicked() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instabug.com"));
        startActivity(browserIntent);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Do you want to get a NullPointerException, because that's how " +
                            "you get a NullPointerException :D")
                    .setPositiveButton("Why not?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            throw new NullPointerException("Test issue in Instabug Sample app");
                        }
                    }).show();
            Instabug.setDialog(alertDialog);
        } else if (id == R.id.nav_maps) {
            startActivity(new Intent(this, GoogleMapsActivity.class));
        } else if (id == R.id.nav_openGl) {
            startActivity(new Intent(this, OpenGLActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(Intent.createChooser(getShareIntent(), getResources().getString(R.string.share_to_friends)));
        } else if (id == R.id.nav_about) {
            Instabug.showIntroMessage();
            // TODO click about to show dialog to test screenshot with dialogs
            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Intent getShareIntent() {
        String sharedText = "Check out this awesome sample app by @instabug\n\nhttps://github.com/Instabug/android-sample";
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, sharedText);
        return shareIntent;
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
                networkLog.Log(urlConnection, jsonParam.toString(), moviesJsonStr);

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
