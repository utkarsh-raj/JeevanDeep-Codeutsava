package com.codeutsava.jeevandeep.donationcampaignlist;

import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;

public interface DonationCampaignCallback {
    void onSuccess(DonationCampaignListResponse bloodBankListResponse);
    void onFailed();
}

