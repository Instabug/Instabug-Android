package com.example.instabug.fcm

import android.content.Intent

import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by vezikon on 6/27/16.
 */
class InstabugInstanceIDListenerService : FirebaseInstanceIdService() {

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    override fun onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        val intent = Intent(this, RegistrationIntentService::class.java)
        startService(intent)
    }

    companion object {

        private const val TAG = "InstabugInstanceIDLS"
    }
    // [END refresh_token]
}