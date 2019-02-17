package com.codeutsava.jeevandeep.utils;

import android.graphics.Bitmap;

//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;


//public class MyFirebaseService extends FirebaseMessagingService {
public class MyFirebaseService {
    private String TAG="MyFirebaseService";
    private static int nid=0;
    private Bitmap bitmap;
    private String imageUri;
    private boolean productNotif=true;

//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        // ...
//
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getClickAction());
//
//        }
//
//        if(remoteMessage.getData().containsKey("product_id")){
//            Keys.product_id= Integer.parseInt(remoteMessage.getData().get("product_id"));
//            if(Keys.product_id!=-1){
//                if(!remoteMessage.getData().get("category_id").isEmpty()){
//                    Keys.category_id= Integer.parseInt(remoteMessage.getData().get("category_id"));
//                }
//                if(!remoteMessage.getData().get("sub_category_id").isEmpty()){
//                    Keys.sub_category_id= Integer.parseInt(remoteMessage.getData().get("sub_category_id"));
//                }
//                if(!remoteMessage.getData().get("category_name").isEmpty()){
//                    Keys.category_name=remoteMessage.getData().get("category_name");
//                }
//                if(!remoteMessage.getData().get("product_position").isEmpty()){
//                    Keys.product_position= Integer.parseInt(remoteMessage.getData().get("product_position"));
//                }
//                if(!remoteMessage.getData().get("sub_category_position").isEmpty()){
//                    Keys.sub_category_position= Integer.parseInt(remoteMessage.getData().get("sub_category_position"));
//                }
//
//            }
//            else
//            {
//                productNotif=false;
//            }
//        }
//
//
//        //Keys.KEY_PRODUCT_POSITION=remoteMessage.getData().get("product_position");
//
//
//        imageUri = remoteMessage.getData().get("image");
//        if(imageUri.isEmpty()){
//            sendNotification(remoteMessage);
//        }
//        else
//        {
//            imageUri= Urls.BASE_URL+ Urls.SUB_NOTIFICATION_IMAGES+imageUri;
//            bitmap=getBitmapfromUrl(imageUri);
//            Log.d("image is:",imageUri);
//            sendImageNotification(remoteMessage,bitmap);
//        }
//    }
//
//    //we need this module only for creating notification when app is in foreground
//    private void sendImageNotification(RemoteMessage messageBody,Bitmap bitmap) {
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        if(productNotif){
//            intent.putExtra(Keys.KEY_FCM_ACTIVITY,false);
//            intent.putExtra(Keys.KEY_PRODUCT_ACTIVITY,true);
//            intent.putExtra(Keys.KEY_CATEGORY_ID, Keys.category_id);
//            intent.putExtra(Keys.KEY_CATEGORY_NAME, Keys.category_name);
//            intent.putExtra(Keys.KEY_SUBCATEGORY_ID, Keys.sub_category_id);
//            intent.putExtra(Keys.KEY_PRODUCT_ID, Keys.product_id);
//        }
//        else
//        {
//            intent.putExtra(Keys.KEY_PRODUCT_ACTIVITY,false);
//            intent.putExtra(Keys.KEY_FCM_ACTIVITY,true);
//
//        }
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ /* Request code*/ , intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setContentTitle("FoodO")
//                .setContentText(""+messageBody.getData().get("message"))
//                .setTicker(messageBody.getData().get("message")+"")
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
//                .setSound(defaultSoundUri)
//                .setPriority(10011)
//
//                .setSmallIcon(R.mipmap.ic_launcher);
//
//        Log.d("value of nid",String.valueOf(nid));
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(nid  /*ID of notification*/ , notificationBuilder.build());
//    }
//
//    private void sendNotification(RemoteMessage remoteMessage)
//    {
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        if(productNotif){
//            intent.putExtra(Keys.KEY_FCM_ACTIVITY,false);
//            intent.putExtra(Keys.KEY_PRODUCT_ACTIVITY,true);
//            intent.putExtra(Keys.KEY_CATEGORY_ID, Keys.category_id);
//            intent.putExtra(Keys.KEY_CATEGORY_NAME, Keys.category_name);
//            intent.putExtra(Keys.KEY_SUBCATEGORY_ID, Keys.sub_category_id);
//            intent.putExtra(Keys.KEY_PRODUCT_ID, Keys.product_id);
//        }
//        else
//        {
//            intent.putExtra(Keys.KEY_PRODUCT_ACTIVITY,false);
//            intent.putExtra(Keys.KEY_FCM_ACTIVITY,true);
//        }
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ /* Request code*/ , intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setContentTitle(remoteMessage.getData().get("title"))
//                .setContentText(remoteMessage.getData().get("message"))
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(remoteMessage.getData().get("title")))
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("message")))
//                .setSound(defaultSoundUri).setSmallIcon(R.mipmap.ic_launcher);
//
//        Log.d("value of nid",String.valueOf(nid));
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(nid  /*ID of notification*/ , notificationBuilder.build());
//
//    }
//
//    /*
//    *To get a Bitmap image from the URL received
//    * */
//    public Bitmap getBitmapfromUrl(String imageUrl) {
//        try {
//            URL url = new URL(imageUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(input);
//            return bitmap;
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//
//        }
//    }
}
