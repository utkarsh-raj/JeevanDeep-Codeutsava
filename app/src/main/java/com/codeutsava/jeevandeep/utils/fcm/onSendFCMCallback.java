package com.codeutsava.jeevandeep.utils.fcm;


import com.codeutsava.jeevandeep.utils.fcm.model.SendFcmResponse;

public interface onSendFCMCallback {

    void onSuccess(SendFcmResponse response);

    void onFailure();
}
