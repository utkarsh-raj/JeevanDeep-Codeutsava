package com.codeutsava.jeevandeep.auth.login;


import com.codeutsava.jeevandeep.auth.login.data.LoginData;

public interface LoginCallBack {

    void onSuccess(LoginData loginData);

    void onFailure();
}
