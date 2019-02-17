package com.codeutsava.jeevandeep.profile;


import com.codeutsava.jeevandeep.profile.data.SignupResponse;

public interface SignupCallBack {

    void onSuccess(SignupResponse response);

    void onFaiure(String message);
}
