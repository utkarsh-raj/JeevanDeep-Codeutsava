package com.codeutsava.jeevandeep.donationcampaignlist.provider;

import com.codeutsava.jeevandeep.donationcampaignlist.DonationCampaignCallback;

public interface DonationCampaignListProvider {
    void getNetworkList(String access_token, DonationCampaignCallback feedCallback);
    void onCancel();
}
