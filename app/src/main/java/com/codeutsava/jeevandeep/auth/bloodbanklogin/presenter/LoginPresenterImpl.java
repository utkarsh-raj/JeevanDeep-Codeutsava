package com.codeutsava.jeevandeep.auth.bloodbanklogin.presenter;


import com.codeutsava.jeevandeep.auth.bloodbanklogin.LoginCallBack;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.data.LoginData;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.provider.LoginProvider;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginProvider loginProvider;

    public LoginPresenterImpl(LoginView loginView, LoginProvider loginProvider) {
        this.loginView = loginView;
        this.loginProvider = loginProvider;
    }

    @Override
    public void requestLogin(String contact_no, String password) {
        loginView.showProgressBar(true);
        loginProvider.requestLogin(contact_no, password, new LoginCallBack() {
            @Override
            public void onSuccess(LoginData loginData) {
                try {
                    if (loginData.isSuccess()) {
                        loginView.showProgressBar(false);
                        loginView.onLoginSuccessful(loginData.getToken());
                    } else {
                        loginView.showProgressBar(false);
                        loginView.showMessage(loginData.getMessage());
//                        loginView.onLoginfailure(contact_no);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure() {
                loginView.showMessage("please try again");
                loginView.showProgressBar(false);
            }
        });
    }
}
