package com.example.vidalgt.blackhatclient;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.nio.channels.Channel;

public class messagingService extends FirebaseMessagingService {


    NotificationManager nm;
    Notification notif;
    static String ns = Context.NOTIFICATION_SERVICE;

    Uri defaultSong = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public messagingService() {
    }

    public void sendNotif(String titulo, String mensaje){
        Notification.Builder builder = new Notification.Builder(this);
        nm = (NotificationManager) getSystemService(ns);
        notif = getDefaultNotification(builder, titulo, mensaje);
        nm.notify(1,notif);
    }



    public Notification getDefaultNotification(Notification.Builder builder, String titulo,String mensaje){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);


        builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(defaultSong);
        return builder.build();
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
String TAG = "Firebase Message";
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotif("Nueva Promocion",remoteMessage.getNotification().getBody());
        }

        sendNotif("Nueva Promocion 2",remoteMessage.getNotification().getBody());
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }
    private void scheduleJob() {
    }

    private void handleNow(){
    }


}
