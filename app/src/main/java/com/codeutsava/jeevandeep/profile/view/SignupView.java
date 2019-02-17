package com.codeutsava.jeevandeep.profile.view;


import com.codeutsava.jeevandeep.profile.data.SignupResponse;

public interface SignupView {
    void showMessage(String message);

    void showProgressBar(boolean show);

    void showProgressDilog(boolean show);

    void onVerified(SignupResponse signupResponse);
}
