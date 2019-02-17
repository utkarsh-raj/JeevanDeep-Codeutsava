package com.codeutsava.jeevandeep.RequestForBlood.presenter;


import com.codeutsava.jeevandeep.RequestForBlood.RequestForBloodCallBack;
import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;
import com.codeutsava.jeevandeep.RequestForBlood.provider.RequestForBloodProvider;
import com.codeutsava.jeevandeep.RequestForBlood.view.RequestForBloodView;

import java.io.File;

public class RequestForBloodPresenterImpl implements RequestForBloodPresenter {

    private RequestForBloodProvider requestForBloodProvider;
    private RequestForBloodView requestForBloodView;

    public RequestForBloodPresenterImpl(RequestForBloodProvider requestForBloodProvider, RequestForBloodView requestForBloodView) {
        this.requestForBloodProvider = requestForBloodProvider;
        this.requestForBloodView = requestForBloodView;
    }

    @Override
    public void requestSignup(String user_name, String mobile_number, String treatment, String hospital, String bloodgroup, String age, String number_of_units, String access_token)
    {
        requestForBloodView.showProgressBar(true);
        requestForBloodView.showProgressDilog(true);
        requestForBloodProvider.getSignupRequest(user_name, mobile_number, treatment, hospital, bloodgroup, age, number_of_units, access_token, new RequestForBloodCallBack() {
            @Override
            public void onSuccess(RequestForBloodResponse response) {
                //Log.d("hello",response.getMessage());
                if (response.getSuccess()) {
                    requestForBloodView.showProgressDilog(false);
                    requestForBloodView.showProgressBar(false);
                    requestForBloodView.onVerified(response);
                } else {
                    requestForBloodView.showProgressBar(false);
                    requestForBloodView.showMessage(response.getMessage());
                }
            }

            @Override
            public void onFaiure(String message) {
                requestForBloodView.showMessage(message);
                requestForBloodView.showProgressBar(false);
            }
        });

    }
}
