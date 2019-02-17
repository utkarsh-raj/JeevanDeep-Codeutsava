package com.codeutsava.jeevandeep.auth.bloodbanklogin.view;

public interface LoginView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void showProgressDilog(boolean show);

    void onLoginSuccessful(String access_token);

    void onLoginfailure(String userNo);
}
