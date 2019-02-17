package com.codeutsava.jeevandeep.auth.login.provider;


import com.codeutsava.jeevandeep.auth.login.LoginCallBack;

public interface LoginProvider {

    void requestLogin(String contact_no, LoginCallBack loginCallBack);
}
