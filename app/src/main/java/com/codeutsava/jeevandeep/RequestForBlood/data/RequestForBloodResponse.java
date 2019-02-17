package com.codeutsava.jeevandeep.RequestForBlood.data;

public class RequestForBloodResponse {

    private Boolean success;
    private String message;

    public RequestForBloodResponse(Boolean success, String message) {
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
