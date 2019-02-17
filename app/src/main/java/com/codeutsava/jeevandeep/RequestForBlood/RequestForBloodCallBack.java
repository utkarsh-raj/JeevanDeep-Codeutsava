package com.codeutsava.jeevandeep.RequestForBlood;


import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;

public interface RequestForBloodCallBack {

    void onSuccess(RequestForBloodResponse response);

    void onFaiure(String message);
}
