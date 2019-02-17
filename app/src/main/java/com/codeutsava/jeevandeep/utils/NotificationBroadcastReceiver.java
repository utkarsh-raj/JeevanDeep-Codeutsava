package com.codeutsava.jeevandeep.utils;//package com.codenicely.services.steelgroup.utils;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
//import android.widget.Toast;
//
//import com.codenicely.services.steelgroup.R;
//
//public class NotificationBroadcastReceiver extends BroadcastReceiver {
//
//    private static String KEY_NOTIFICATION_ID = "key_noticiation_id";
//    private static String KEY_MESSAGE_ID = "key_message_id";
//
//    public NotificationBroadcastReceiver() {
//    }
//
//
//    public static Intent getReplyMessageIntent(Context context, int notificationId, int messageId) {
//        Intent intent = new Intent(context, NotificationBroadcastReceiver.class);
//        intent.setAction(REPLY_ACTION);
//        intent.putExtra(KEY_NOTIFICATION_ID, notificationId);
//        intent.putExtra(KEY_MESSAGE_ID, messageId);
//        return intent;
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (REPLY_ACTION.equals(intent.getAction())) {
//            // do whatever you want with the message. Send to the server or add to the db.
//            // for this tutorial, we'll just show it in a toast;
//            CharSequence message = getReplyMessage(intent);
//            int messageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);
//
//            Toast.makeText(context, "Message ID: " + messageId + "\nMessage: " + message,
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updateNotification(Context context, int notifyId) {
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setSmallIcon(android.R.drawable.stat_notify_chat)
//                .setContentText("Amrita Broadcast Receiver Replied");
//
//
//        notificationManager.notify(notifyId, builder.build());
//    }
//}
