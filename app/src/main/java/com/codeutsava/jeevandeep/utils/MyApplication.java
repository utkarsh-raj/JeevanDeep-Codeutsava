package com.codeutsava.jeevandeep.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

//import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by meghal on 11/10/16.
 */

public class MyApplication extends Application {

    private static Context context;
    private static String fcm_token;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Answers(), new Crashlytics());
        context = this;

        //productsansregular
/*
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Lato-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Lato-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/Lato-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/Lato-Regular.ttf");
*/


        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/productsansregular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/productsansregular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/productsansregular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/productsansregular.ttf");
    }

    public static String getFcm_token() {
//        fcm_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Fcm is " + fcm_token);
        return fcm_token;
    }

    public static Context getContext() {
        return context;
    }


}
