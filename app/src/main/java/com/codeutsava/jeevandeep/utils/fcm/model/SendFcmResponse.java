package com.codeutsava.jeevandeep.utils.fcm.model;

public class SendFcmResponse {
    private boolean success;
    private String message;

    public SendFcmResponse(boolean success, String message) {
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
