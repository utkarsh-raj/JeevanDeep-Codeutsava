package com.codeutsava.jeevandeep.profile.presenter;


import com.codeutsava.jeevandeep.profile.SignupCallBack;
import com.codeutsava.jeevandeep.profile.data.SignupResponse;
import com.codeutsava.jeevandeep.profile.provider.SignupProvider;
import com.codeutsava.jeevandeep.profile.view.SignupView;

import java.io.File;

public class SignupPresenterImpl implements SignupPresenter {

    private SignupProvider signupProvider;
    private SignupView signupView;

    public SignupPresenterImpl(SignupView signupView, SignupProvider signupProvider) {
        this.signupProvider = signupProvider;
        this.signupView = signupView;
    }

    @Override
    public void requestSignup(String user_name, String location, String bloodgroup, File profile_image, boolean is_imageupdated) {
        signupView.showProgressBar(true);
        signupView.showProgressDilog(true);
        signupProvider.getSignupRequest(user_name,location, bloodgroup, profile_image, is_imageupdated, new SignupCallBack() {
            @Override
            public void onSuccess(SignupResponse response) {
                //Log.d("hello",response.getMessage());
                if (response.getSuccess()) {
                    signupView.showProgressDilog(false);
                    signupView.showProgressBar(false);
                    signupView.onVerified(response);
                } else {
                    signupView.showProgressBar(false);
                    signupView.showMessage(response.getMessage());
                }
            }

            @Override
            public void onFaiure(String message) {
                signupView.showMessage(message);
                signupView.showProgressBar(false);
            }
        });

    }
}
