package com.codeutsava.jeevandeep.bloodbank;

import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;

public interface BloodBankCallback {
    void onSuccess(BloodBankListResponse bloodBankListResponse);
    void onFailed();
}

