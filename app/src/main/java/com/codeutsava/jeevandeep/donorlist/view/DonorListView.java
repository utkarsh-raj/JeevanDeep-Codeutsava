package com.codeutsava.jeevandeep.donorlist.view;

import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;

public interface DonorListView {
    void showProgressbar(boolean show);
    void showMessage(String message);
    void setFeedList(DonorListResponse feedListResponse);

}