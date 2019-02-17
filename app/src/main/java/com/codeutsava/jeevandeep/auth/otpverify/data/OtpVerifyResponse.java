package com.codeutsava.jeevandeep.auth.otpverify.data;

public class OtpVerifyResponse {

    private boolean success;
    private String message;
    private String access_token;


    public OtpVerifyResponse(boolean success, String message, String access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return access_token;
    }
}
