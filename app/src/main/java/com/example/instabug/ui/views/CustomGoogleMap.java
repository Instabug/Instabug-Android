package com.example.instabug.ui.views;

import android.graphics.Bitmap;
import android.support.annotation.Size;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.instabug.library.internal.layer.CapturableView;

/**
 * @author mSobhy
 */
public class CustomGoogleMap implements CapturableView {

    private View mapFragment;
    private GoogleMap googleMap;

    public CustomGoogleMap(View mapFragment, GoogleMap googleMap) {
        this.mapFragment = mapFragment;
        this.googleMap = googleMap;
    }

    @Override
    public void snapshot(final SnapshotPreparationCallback snapshotPreparationCallback) {
        googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
//                Bitmap manipulatedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//                for (int i = 0; i < bitmap.getWidth(); i++) {
//                    for (int j = 0; j < bitmap.getHeight(); j++) {
//                        int p = bitmap.getPixel(i, j);
//                        int r = Color.red(p);
//                        int g = Color.green(p);
//                        int b = Color.blue(p);
//                        int alpha = Color.alpha(p);
//
//                        r = 0;
//                        g = 0;
//                        b = b + 150;
//                        alpha = 0;
//                        manipulatedBitmap.setPixel(i, j, Color.argb(Color.alpha(p), r, g, b));
//                    }
//                }
                snapshotPreparationCallback.onSnapshotReady(bitmap);
            }
        });
    }

    @Override
    public boolean isVisible() {
        return mapFragment.getVisibility() == View.VISIBLE;
    }

    @Override
    public void getLocationOnScreen(@Size(2) int[] location) {
        mapFragment.getLocationOnScreen(location);
    }
}
