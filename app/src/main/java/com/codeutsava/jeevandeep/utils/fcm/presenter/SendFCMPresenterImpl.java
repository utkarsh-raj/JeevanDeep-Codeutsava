package com.codeutsava.jeevandeep.utils.fcm.presenter;

import android.util.Log;

import com.codeutsava.jeevandeep.utils.fcm.RetrofitSendFCMProvider;
import com.codeutsava.jeevandeep.utils.fcm.model.SendFcmResponse;
import com.codeutsava.jeevandeep.utils.fcm.onSendFCMCallback;

public class SendFCMPresenterImpl implements  SendFCMPresenter{


    private RetrofitSendFCMProvider retrofitSendFCMProvider;

    public SendFCMPresenterImpl(RetrofitSendFCMProvider retrofitSendFCMProvider) {
        this.retrofitSendFCMProvider = retrofitSendFCMProvider;
    }


    @Override
    public void sendFCM(String access_token, String fcm) {
        retrofitSendFCMProvider.sendFCM(access_token, fcm, new onSendFCMCallback() {
            @Override
            public void onSuccess(SendFcmResponse response) {
                Log.d("Debug Amrita", "FCM presenter success");
            }

            @Override
            public void onFailure() {
                Log.d("Debug Amrita", "FCM presenter failure");

            }
        });

    }
}
