package com.codeutsava.jeevandeep.utils.resendotp.presenter;

import android.util.Log;

import com.codeutsava.jeevandeep.utils.resendotp.RetrofitRegenerateOtpProvider;
import com.codeutsava.jeevandeep.utils.resendotp.model.RegenerateOtpResponse;
import com.codeutsava.jeevandeep.utils.resendotp.onRegenerateOtpCallback;


public class RegenerateOtpPresenterImpl implements RegenerateOtpPresenter {


    private RetrofitRegenerateOtpProvider retrofitRegenerateOtpProvider;

    public RegenerateOtpPresenterImpl(RetrofitRegenerateOtpProvider retrofitRegenerateOtpProvider) {
        this.retrofitRegenerateOtpProvider = retrofitRegenerateOtpProvider;
    }

    @Override
    public void regenerateOtp(String mobile) {
        retrofitRegenerateOtpProvider.regenerateOtp(mobile, new onRegenerateOtpCallback(){
                    @Override
                    public void onSuccess(RegenerateOtpResponse regenerateOtpResponse) {
                        Log.d("Debug Amrita", "FCM presenter success");
                    }

                    @Override
                    public void onFailure() {
                        Log.d("Debug Amrita", "FCM presenter failure");
                    }
                });

    }
}
