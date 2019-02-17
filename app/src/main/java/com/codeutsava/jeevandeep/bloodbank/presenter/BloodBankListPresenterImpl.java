package com.codeutsava.jeevandeep.bloodbank.presenter;


import com.codeutsava.jeevandeep.bloodbank.BloodBankCallback;
import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;
import com.codeutsava.jeevandeep.bloodbank.provider.BloodBankListProvider;
import com.codeutsava.jeevandeep.bloodbank.view.BloodBankListView;
import com.codeutsava.jeevandeep.donationcampaignlist.DonationCampaignCallback;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
public class BloodBankListPresenterImpl implements BloodBankListPresenter {

    private BloodBankListView bloodBankListView;
    private BloodBankListProvider bloodBankListProvider;

    public BloodBankListPresenterImpl(BloodBankListView bloodBankListView, BloodBankListProvider bloodBankListProvider) {
        this.bloodBankListView = bloodBankListView;
        this.bloodBankListProvider = bloodBankListProvider;
    }

    @Override
    public void getFeedList(String access_token) {
        bloodBankListView.showProgressbar(true);
        bloodBankListProvider.getNetworkList(access_token, new BloodBankCallback() {
            @Override
            public void onSuccess(BloodBankListResponse donorListResponse) {
                bloodBankListView.showProgressbar(false);
                if (donorListResponse.isSuccess()){
                    bloodBankListView.setFeedList(donorListResponse);
                }else {
                    bloodBankListView.showMessage(donorListResponse.getMessage());
                }
            }

            @Override
            public void onFailed() {
                bloodBankListView.showProgressbar(false);
                bloodBankListView.showMessage("Unable to connect to server");
            }
        });
    }

    @Override
    public void onCallCancel() {
        bloodBankListView.showProgressbar(false);
        bloodBankListProvider.onCancel();
    }
}
