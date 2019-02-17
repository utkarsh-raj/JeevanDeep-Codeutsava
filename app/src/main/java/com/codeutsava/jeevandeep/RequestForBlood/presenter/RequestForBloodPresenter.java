package com.codeutsava.jeevandeep.RequestForBlood.presenter;

import java.io.File;

public interface RequestForBloodPresenter {

    void requestSignup(String user_name, String mobile_number, String treatment, String hospital, String bloodgroup, String age, String number_of_units, String access_token);
}
