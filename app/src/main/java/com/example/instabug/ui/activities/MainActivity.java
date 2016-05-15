package com.example.instabug.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instabug.R;
import com.instabug.library.Instabug;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    ImageView headerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
    }

    @OnClick(R.id.feedback_fab)
    public void onFeedbackFABClicked() {
        Instabug.invoke();
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
}
