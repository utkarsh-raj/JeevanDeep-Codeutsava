package com.codeutsava.jeevandeep.donationcampaignlist.data;

import java.util.List;

public class DonationCampaignListResponse {
    private boolean success;
    private String message;
    private List<DonationCampaignItem> campaign_list;

    public DonationCampaignListResponse(boolean success, String message, List<DonationCampaignItem> google_news_list) {
        this.success = success;
        this.message = message;
        this.campaign_list = google_news_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<DonationCampaignItem> getNews_feed_list() {
        return campaign_list;
    }

}