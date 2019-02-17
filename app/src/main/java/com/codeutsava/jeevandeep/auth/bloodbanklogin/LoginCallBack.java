package com.codeutsava.jeevandeep.auth.bloodbanklogin;


import com.codeutsava.jeevandeep.auth.bloodbanklogin.data.LoginData;

public interface LoginCallBack {

    void onSuccess(LoginData loginData);

    void onFailure();
}
