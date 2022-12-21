package com.example.instabug.fcm;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.instabug.chat.Replies;

/**
 * Created by vezikon on 6/27/16.
 */
public class InstabugInstanceIDListenerService extends FirebaseMessagingService {

    /**
     * Called when a new token for the default Firebase project is generated.
     * This is invoked after app install when a token is first generated, and again if the token changes.
     *
     * @param token – The token used for sending messages to this application instance. This token is the same as the one retrieved by FirebaseMessaging.getToken().
     */
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Replies.setPushNotificationRegistrationToken(token);
    }
}