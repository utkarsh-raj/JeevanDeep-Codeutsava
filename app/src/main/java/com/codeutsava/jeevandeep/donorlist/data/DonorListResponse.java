package com.codeutsava.jeevandeep.donorlist.data;
import java.util.List;

public class DonorListResponse {
    private boolean success;
    private String message;
    private List<DonorItem> donor_list;

    public DonorListResponse(boolean success, String message, List<DonorItem> google_news_list) {
        this.success = success;
        this.message = message;
        this.donor_list = google_news_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<DonorItem> getNews_feed_list() {
        return donor_list;
    }

}