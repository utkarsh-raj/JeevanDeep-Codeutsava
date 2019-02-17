package com.codeutsava.jeevandeep.bloodbank.view;

import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;

public interface BloodBankListView {
    void showProgressbar(boolean show);
    void showMessage(String message);
    void setFeedList(BloodBankListResponse feedListResponse);
}