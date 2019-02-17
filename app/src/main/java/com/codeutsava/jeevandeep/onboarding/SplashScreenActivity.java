package com.codeutsava.jeevandeep.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.login.view.LoginActivity;
import com.codeutsava.jeevandeep.home.HomeActivity;
import com.codeutsava.jeevandeep.introsliders.IntroSliderActivity;
import com.codeutsava.jeevandeep.utils.SharedPrefs;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler;
    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPrefs = new SharedPrefs(this);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPrefs.isFirstLaunch()){
                    startActivity(new Intent(SplashScreenActivity.this, IntroSliderActivity.class));
                }else {
                    if(sharedPrefs.isLoggedInOurApp()){
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    }
                }
                finish();
            }
        }, 1800);

    }
}
