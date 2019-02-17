package com.codeutsava.jeevandeep.bloodbank.provider;


import com.codeutsava.jeevandeep.bloodbank.BloodBankCallback;

public interface BloodBankListProvider {
    void getNetworkList(String access_token, BloodBankCallback feedCallback);
    void onCancel();
}
