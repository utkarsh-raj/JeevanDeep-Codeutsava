package com.codeutsava.jeevandeep.utils.resendotp.model;

public class RegenerateOtpResponse {
    private boolean success;
    private String message;

    public RegenerateOtpResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
