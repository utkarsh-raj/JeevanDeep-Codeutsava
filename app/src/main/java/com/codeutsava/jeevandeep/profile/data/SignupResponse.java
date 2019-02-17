package com.codeutsava.jeevandeep.profile.data;

public class SignupResponse {

    private Boolean success;
    private String message;

    public SignupResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;

    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
