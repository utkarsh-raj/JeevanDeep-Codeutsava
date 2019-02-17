package com.codeutsava.jeevandeep.donorlist.provider;

import com.codeutsava.jeevandeep.donorlist.DonorCallback;

public interface DonorListProvider {
    void getNetworkList(String access_token, DonorCallback feedCallback);
    void onCancel();
}
