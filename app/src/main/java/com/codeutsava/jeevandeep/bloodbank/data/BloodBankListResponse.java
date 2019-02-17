package com.codeutsava.jeevandeep.bloodbank.data;


import java.util.List;

public class BloodBankListResponse {
    private boolean success;
    private String message;
    private List<BloodBankItem> bank_list;

    public BloodBankListResponse(boolean success, String message, List<BloodBankItem> google_news_list) {
        this.success = success;
        this.message = message;
        this.bank_list = google_news_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<BloodBankItem> getNews_feed_list() {
        return bank_list;
    }

}