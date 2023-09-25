package com.example.hypertextassassins;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Push_notification extends FirebaseMessagingService {
    String TAG="Hello";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String text=remoteMessage.getNotification().getBody();
        final String CHANNEL_ID="Notice";
        NotificationChannel channel=new NotificationChannel(
                CHANNEL_ID,"Notice", NotificationManager.IMPORTANCE_DEFAULT
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification=new Notification.Builder(this,CHANNEL_ID).setContentText(text).setContentTitle(title).setSmallIcon(R.drawable.app_logo).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,notification.build());
        Log.d("Hello","Notification Recived");
        super.onMessageReceived(remoteMessage);
}
}
