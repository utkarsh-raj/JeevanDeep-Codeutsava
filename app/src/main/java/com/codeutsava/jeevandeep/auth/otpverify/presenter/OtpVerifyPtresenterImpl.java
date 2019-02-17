package com.codeutsava.jeevandeep.auth.otpverify.presenter;


import com.codeutsava.jeevandeep.auth.otpverify.data.OtpVerifyResponse;
import com.codeutsava.jeevandeep.auth.otpverify.provider.OtpVerifyProvider;
import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyCallback;
import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyView;

public class OtpVerifyPtresenterImpl implements OtpVerfiyPresenter {

    private OtpVerifyView verifyView;
    private OtpVerifyProvider verifyprovider;

    public OtpVerifyPtresenterImpl(OtpVerifyView verifyView, OtpVerifyProvider verifyprovider) {
        this.verifyView = verifyView;
        this.verifyprovider = verifyprovider;
    }


    @Override
    public void verifyOtp(String access_token, final int otp) {
        verifyView.showProgressBar(true);
        verifyprovider.verifyOtp(access_token, otp, new OtpVerifyCallback() {
            @Override
            public void onSuccess(OtpVerifyResponse verifyResponse) {
                if (verifyResponse.isSuccess()) {
                    verifyView.showProgressBar(false);
                    verifyView.onOtpVerified(verifyResponse.getToken());

                } else {
                    verifyView.showProgressBar(false);
                    verifyView.showMessage("Invalid Otp");
                    verifyView.onOtpNotVerified();

                }
            }

            @Override
            public void onFailure(String message) {
                verifyView.showProgressBar(false);
                verifyView.showMessage("Invalid otp");

            }
        });


    }
}
