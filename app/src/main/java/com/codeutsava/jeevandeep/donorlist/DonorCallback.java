package com.codeutsava.jeevandeep.donorlist;

import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;

public interface DonorCallback {
    void onSuccess(DonorListResponse donorListResponse);
    void onFailed();
}

