package com.codeutsava.jeevandeep.bloodbankinventory.provider;

import com.codeutsava.jeevandeep.bloodbankinventory.SignupCallBack;

import java.io.File;

public interface SignupProvider {

    void getSignupRequest(String user_name, String location, String bloodgroup, String access_token, File profile_image, boolean is_imageupdated, SignupCallBack callBack);
}

