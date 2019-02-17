package com.codeutsava.jeevandeep.donationcampaignlist.presenter;


import com.codeutsava.jeevandeep.donationcampaignlist.DonationCampaignCallback;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.donationcampaignlist.provider.DonationCampaignListProvider;
import com.codeutsava.jeevandeep.donationcampaignlist.view.DonationCampaignListView;
import com.codeutsava.jeevandeep.donorlist.DonorCallback;
import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;
import com.codeutsava.jeevandeep.donorlist.presenter.DonorListPresenter;

public class DonationCampaignListPresenterImpl implements DonationCampaignListPresenter {
    private DonationCampaignListView donationCampaignListView;
    private DonationCampaignListProvider donationCampaignListProvider;

    public DonationCampaignListPresenterImpl(DonationCampaignListView donationCampaignListView, DonationCampaignListProvider donationCampaignListProvider) {
        this.donationCampaignListView = donationCampaignListView;
        this.donationCampaignListProvider = donationCampaignListProvider;
    }

    @Override
    public void getFeedList(String access_token) {
        donationCampaignListView.showProgressbar(true);
        donationCampaignListProvider.getNetworkList(access_token, new DonationCampaignCallback() {
            @Override
            public void onSuccess(DonationCampaignListResponse donorListResponse) {
                donationCampaignListView.showProgressbar(false);
                if (donorListResponse.isSuccess()){
                    donationCampaignListView.setFeedList(donorListResponse);
                }else {
                    donationCampaignListView.showMessage(donorListResponse.getMessage());
                }
            }

            @Override
            public void onFailed() {
                donationCampaignListView.showProgressbar(false);
                donationCampaignListView.showMessage("Unable to connect to server");
            }
        });
    }

    @Override
    public void onCallCancel() {
        donationCampaignListView.showProgressbar(false);
        donationCampaignListProvider.onCancel();
    }
}
