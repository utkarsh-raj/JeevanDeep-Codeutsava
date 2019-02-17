package com.codeutsava.jeevandeep.profile.provider;

import com.codeutsava.jeevandeep.profile.SignupCallBack;

import java.io.File;

public interface SignupProvider {

    void getSignupRequest(String user_name, String location, String bloodgroup, File profile_image, boolean is_imageupdated, SignupCallBack callBack);
}

