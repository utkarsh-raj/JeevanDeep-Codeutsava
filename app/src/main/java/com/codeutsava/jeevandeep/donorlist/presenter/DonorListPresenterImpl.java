package com.codeutsava.jeevandeep.donorlist.presenter;


import com.codeutsava.jeevandeep.donorlist.DonorCallback;
import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;
import com.codeutsava.jeevandeep.donorlist.provider.DonorListProvider;
import com.codeutsava.jeevandeep.donorlist.view.DonorListView;

public class DonorListPresenterImpl implements DonorListPresenter {
    private DonorListView donorListView;
    private DonorListProvider donorListProvider;

    public DonorListPresenterImpl(DonorListView donorListView, DonorListProvider donorListProvider) {
        this.donorListView = donorListView;
        this.donorListProvider = donorListProvider;
    }

    @Override
    public void getFeedList(String access_token) {
        donorListView.showProgressbar(true);
        donorListProvider.getNetworkList(access_token, new DonorCallback() {
            @Override
            public void onSuccess(DonorListResponse donorListResponse) {
                donorListView.showProgressbar(false);
                if (donorListResponse.isSuccess()){
                    donorListView.setFeedList(donorListResponse);
                }else {
                    donorListView.showMessage(donorListResponse.getMessage());
                }
            }

            @Override
            public void onFailed() {
                donorListView.showProgressbar(false);
                donorListView.showMessage("Unable to connect to server");
            }
        });
    }

    @Override
    public void onCallCancel() {
        donorListView.showProgressbar(false);
        donorListProvider.onCancel();
    }
}
