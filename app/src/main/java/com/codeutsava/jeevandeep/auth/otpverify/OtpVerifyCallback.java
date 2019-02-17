package com.codeutsava.jeevandeep.auth.otpverify.view;


import com.codeutsava.jeevandeep.auth.otpverify.data.OtpVerifyResponse;

public interface OtpVerifyCallback {
    void onSuccess(OtpVerifyResponse verifyResponse);

    void onFailure(String message);
}
