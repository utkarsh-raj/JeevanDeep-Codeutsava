package com.codeutsava.jeevandeep.bloodbankinventory.presenter;

import java.io.File;

public interface SignupPresenter {

    void requestSignup(String user_name, String location, String bloodgroup, String access_token, File profile_image, boolean is_imageupdated);
}
