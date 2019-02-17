package com.codeutsava.jeevandeep.utils.resendotp;


import com.codeutsava.jeevandeep.utils.resendotp.model.RegenerateOtpResponse;

public interface onRegenerateOtpCallback {

    void onSuccess(RegenerateOtpResponse regenerateOtpResponse);

    void onFailure();
}
