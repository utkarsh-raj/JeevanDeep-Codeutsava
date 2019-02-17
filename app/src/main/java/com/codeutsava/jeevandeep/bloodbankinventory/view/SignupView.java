package com.codeutsava.jeevandeep.bloodbankinventory.view;


import com.codeutsava.jeevandeep.bloodbankinventory.data.InventoryUpdateResponse;

public interface SignupView {
    void showMessage(String message);

    void showProgressBar(boolean show);

    void showProgressDilog(boolean show);

    void onVerified(InventoryUpdateResponse inventoryUpdateResponse);
}
