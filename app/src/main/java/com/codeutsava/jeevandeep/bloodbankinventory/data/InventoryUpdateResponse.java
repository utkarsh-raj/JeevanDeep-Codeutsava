package com.codeutsava.jeevandeep.bloodbankinventory.data;

public class InventoryUpdateResponse {

    private Boolean success;
    private String message;

    public InventoryUpdateResponse(Boolean success, String message) {
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
