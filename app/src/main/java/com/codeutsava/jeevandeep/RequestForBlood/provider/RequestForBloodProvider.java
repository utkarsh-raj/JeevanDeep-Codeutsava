package com.codeutsava.jeevandeep.RequestForBlood.provider;


import com.codeutsava.jeevandeep.RequestForBlood.RequestForBloodCallBack;


public interface RequestForBloodProvider {

    void getSignupRequest(String user_name, String mobile_number, String treatment, String hospital, String bloodgroup, String age, String number_of_units, String access_token, RequestForBloodCallBack callBack);
}

