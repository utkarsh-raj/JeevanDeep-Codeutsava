package com.codeutsava.jeevandeep.auth.login.data;

public class LoginData {

    private boolean success;
    private String message;
    private String access_token;
    private boolean verify_password;

    public LoginData(boolean success, String message, String access_token, boolean verify_password) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
        this.verify_password = verify_password;
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

    public boolean isVerify_password() {
        return verify_password;
    }
}
