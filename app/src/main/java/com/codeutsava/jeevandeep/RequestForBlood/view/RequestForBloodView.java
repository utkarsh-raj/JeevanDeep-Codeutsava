package com.codeutsava.jeevandeep.RequestForBlood.view;

import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;

public interface RequestForBloodView {
    void showMessage(String message);

    void showProgressBar(boolean show);

    void showProgressDilog(boolean show);

    void onVerified(RequestForBloodResponse signupResponse);
}
