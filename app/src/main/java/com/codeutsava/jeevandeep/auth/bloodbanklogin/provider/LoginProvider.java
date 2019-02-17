package com.codeutsava.jeevandeep.auth.bloodbanklogin.provider;


import com.codeutsava.jeevandeep.auth.bloodbanklogin.LoginCallBack;

public interface LoginProvider {

    void requestLogin(String contact_no, String password, LoginCallBack loginCallBack);
}
