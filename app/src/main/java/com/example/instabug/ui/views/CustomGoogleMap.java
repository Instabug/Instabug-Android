package com.example.instabug.ui.views;

import android.graphics.Bitmap;
import android.support.annotation.Size;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.instabug.library.internal.layer.CapturableView;

/**
 * @author mSobhy
 */
public class CustomGoogleMap  {

    private View mapFragment;
    private GoogleMap googleMap;

    public CustomGoogleMap(View mapFragment, GoogleMap googleMap) {
        this.mapFragment = mapFragment;
        this.googleMap = googleMap;
    }
}
