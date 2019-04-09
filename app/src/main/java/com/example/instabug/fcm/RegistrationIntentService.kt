package com.example.instabug.fcm

import android.app.IntentService
import android.content.Intent
import android.preference.PreferenceManager
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.instabug.chat.Replies


/**
 * Created by vezikon on 6/27/16.
 */
class RegistrationIntentService : IntentService(TAG) {

    override fun onHandleIntent(intent: Intent?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        try {
            // [START register_for_fcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.fcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            val token = FirebaseInstanceId.getInstance().token
            // [END get_token]
            Log.i(TAG, "FCM Registration Token: $token")

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token)

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply()
            // [END register_for_fcm]
        } catch (e: Exception) {
            Log.d(TAG, "Failed to complete token refresh", e)
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply()
        }

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        val registrationComplete = Intent(QuickstartPreferences.REGISTRATION_COMPLETE)
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete)
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's FCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // send your registration token to Instabug
        Replies.setPushNotificationRegistrationToken(token)
    }

    companion object {


        private const val TAG = "RegIntentService"
    }

}
