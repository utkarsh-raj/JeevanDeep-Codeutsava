package com.codeutsava.jeevandeep.donationcampaignlist.view;

import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;

public interface DonationCampaignListView {
    void showProgressbar(boolean show);
    void showMessage(String message);
    void setFeedList(DonationCampaignListResponse feedListResponse);
}