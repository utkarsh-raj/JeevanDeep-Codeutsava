package com.codeutsava.jeevandeep.profile.presenter;

import java.io.File;

public interface SignupPresenter {

    void requestSignup(String user_name, String location, String bloodgroup, File profile_image, boolean is_imageupdated);
}
