package com.codeutsava.jeevandeep.auth.otpverify.view;

public interface OtpVerifyView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void onOtpVerified(String access_token);

    void onOtpNotVerified();

}
