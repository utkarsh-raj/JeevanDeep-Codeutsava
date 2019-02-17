package com.codeutsava.jeevandeep.auth.otpverify.provider;


import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyCallback;

public interface OtpVerifyProvider {

    void verifyOtp(String access_token, int otp, OtpVerifyCallback otpVerifyCallBack);

    void onFailed(String message);

}

