package com.codeutsava.jeevandeep.bloodbankinventory;


import com.codeutsava.jeevandeep.bloodbankinventory.data.InventoryUpdateResponse;

public interface SignupCallBack {

    void onSuccess(InventoryUpdateResponse response);

    void onFaiure(String message);
}
