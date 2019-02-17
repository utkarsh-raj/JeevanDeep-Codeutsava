package com.codeutsava.jeevandeep;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.os.Bundle;
import android.util.Log;

import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.codeutsava.jeevandeep.utils.fcm.RetrofitSendFCMProvider;
import com.codeutsava.jeevandeep.utils.fcm.model.SendFcmResponse;
import com.codeutsava.jeevandeep.utils.fcm.onSendFCMCallback;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;



public class MyFirebaseMessagingService extends FirebaseMessagingService  {

    private SharedPrefs sharedPrefs;
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    public static final String CHANNEL_ID = "1";

    public static String TAG = "FIREBASE_SERVICE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        createNotificationChannel();

        sharedPrefs = new SharedPrefs(getApplicationContext());

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if(Boolean.valueOf(remoteMessage.getData().get("isFlash"))){
            final EventBus myEventBus = EventBus.getDefault();
            myEventBus.postSticky(new EventBusNotifData(remoteMessage));

            Intent intent = new Intent(this, FullScreenActivity.class);
            startActivity(intent);
        }



        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(token);

        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        RetrofitSendFCMProvider retrofitSendFCMProvider = new RetrofitSendFCMProvider();
//        retrofitSendFCMProvider.sendFCM(sharedPrefs.getAccessToken(), token);
        retrofitSendFCMProvider.sendFCM(sharedPrefs.getAccessToken(), token, new onSendFCMCallback() {
            @Override
            public void onSuccess(SendFcmResponse sendFcmResponse) {
                Log.d("Debug Amrita", "FCM presenter failure");
            }

            @Override
            public void onFailure() {
                Log.d("Debug Amrita", "FCM presenter failure");
            }
        });
    }

    public void createNotification(RemoteMessage remoteMessage){

        //FOR NORMAL API 21
        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle(remoteMessage.getData().get("group_name"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("message")))
                .setAutoCancel(true)
                .setVibrate(new long[] { 250, 250, 250, 250, 250 })
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);


        if(Integer.parseInt(remoteMessage.getData().get("type"))==1) {
            builder.setContentText(remoteMessage.getData().get("user_name")  + "\n"+ "Sent an image.");
        }
        else if(Integer.parseInt(remoteMessage.getData().get("type"))==2) {
            builder.setContentText(remoteMessage.getData().get("user_name")  + "\n"+ "Sent a video.");
        }
        else if(Integer.parseInt(remoteMessage.getData().get("type"))==3) {
            builder.setContentText(remoteMessage.getData().get("user_name")  + "\n"+ "Sent a document.");
        }
        else {
            builder.setContentText(remoteMessage.getData().get("user_name")  + "\n"+ remoteMessage.getData().get("text"));
        }
//        String replyLabel = "Enter your reply here";
//        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
//                .setLabel(replyLabel)
//                .build();

        int randomRequestCode = new Random().nextInt(54325);

        //PendingIntent that restarts the current activity instance.
//        Intent resultIntent;
//        resultIntent = new Intent(this, GroupChatActivity.class);
//        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        resultIntent.putExtra("group_table_id", Integer.parseInt(remoteMessage.getData().get("group_table_id")));
//        resultIntent.putExtra("group_name", remoteMessage.getData().get("group_name"));
//        resultIntent.putExtra("notification_id", Integer.parseInt(remoteMessage.getData().get("notification_id")));



        //Set a unique request code for this pending intent
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, randomRequestCode, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(resultPendingIntent);

        //Notification Action with RemoteInput instance added.
//        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
//                android.R.drawable.sym_action_chat, "REPLY", resultPendingIntent)
////                .addRemoteInput(remoteInput)
//                .setAllowGeneratedReplies(true)
//                .build();

        //Notification.Action instance added to Notification Builder.
//        builder.addAction(replyAction);

//
//        Intent intent = new Intent(this, GroupChatActivity.class);
//        intent.putExtra("notificationId", NOTIFICATION_ID);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "DISMISS", dismissIntent);

        //Create Notification.
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(Integer.parseInt(remoteMessage.getData().get("notification_id")), builder.build());


    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.message_channel);
            String description = getString(R.string.message_channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private CharSequence getReplyMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);

        }
        return null;
    }




}

