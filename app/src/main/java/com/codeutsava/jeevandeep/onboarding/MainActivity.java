package com.codeutsava.jeevandeep.onboarding;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.codeutsava.jeevandeep.utils.fcm.RetrofitSendFCMProvider;
import com.codeutsava.jeevandeep.utils.fcm.presenter.SendFCMPresenter;
import com.codeutsava.jeevandeep.utils.fcm.presenter.SendFCMPresenterImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private SendFCMPresenter sendFCMPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPrefs sharedPrefs= new SharedPrefs(getApplicationContext());

        sendFCMPresenter = new SendFCMPresenterImpl(new RetrofitSendFCMProvider());

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        sendFCMPresenter.sendFCM(sharedPrefs.getAccessToken(), token);
                    }
                });
    }
}
