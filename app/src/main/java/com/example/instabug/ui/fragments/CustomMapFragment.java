package com.example.instabug.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;

public class CustomMapFragment extends SupportMapFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof Callbacks) {
            ((Callbacks) getActivity()).onMapLoaded(this);
        }
    }

    public interface Callbacks {
        void onMapLoaded(SupportMapFragment mapFragment);
    }
}
