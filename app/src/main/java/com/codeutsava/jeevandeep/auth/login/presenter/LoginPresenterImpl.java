package com.codeutsava.jeevandeep.auth.login.presenter;


import com.codeutsava.jeevandeep.auth.login.LoginCallBack;
import com.codeutsava.jeevandeep.auth.login.data.LoginData;
import com.codeutsava.jeevandeep.auth.login.provider.LoginProvider;
import com.codeutsava.jeevandeep.auth.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginProvider loginProvider;

    public LoginPresenterImpl(LoginView loginView, LoginProvider loginProvider) {
        this.loginView = loginView;
        this.loginProvider = loginProvider;
    }

    @Override
    public void requestLogin(String contact_no) {
        loginView.showProgressBar(true);
        loginProvider.requestLogin(contact_no, new LoginCallBack() {
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
