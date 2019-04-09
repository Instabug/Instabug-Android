package com.example.instabug.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.example.instabug.R;
import com.example.instabug.ui.activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.instabug.chat.Replies;

/**
 * Created by vezikon on 6/27/16.
 */
public class InstabugFCMListenerService extends FirebaseMessagingService {
    private static final String TAG = "FCMListenerService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String message = remoteMessage.getData().get("message");
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message: " + message);

        //Check first if notification related to Instabug or not
        if (Replies.isInstabugNotification(remoteMessage.getData())) {
            //Shown notification related to Instabug
            Replies.showNotification(remoteMessage.getData());
        } else {
            sendNotification(message);
        }

    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param message FCM message received.
     */
    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
