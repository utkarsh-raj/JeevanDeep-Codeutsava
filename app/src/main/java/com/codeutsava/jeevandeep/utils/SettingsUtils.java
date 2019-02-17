//package com.codeutsava.jeevandeep.utils;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.provider.Settings;
//
//public class SettingsUtils {
//
//    public static void showSettingsDialog(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Need Permissions");
//        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
//        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
//            dialog.cancel();
//            openSettings(context);
//        });
//        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
//        builder.show();
//    }
//
//    public static void openSettings(Context context) {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//        intent.setData(uri);
//        context.startActivity(intent);
//    }
//
//}
