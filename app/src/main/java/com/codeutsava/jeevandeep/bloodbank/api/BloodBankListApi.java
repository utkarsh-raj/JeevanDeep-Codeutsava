package com.codeutsava.jeevandeep.bloodbank.api;

import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BloodBankListApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_BLOOD_BANK_LIST)
    Call<BloodBankListResponse> getFeedList(
            @Field("access_token") String access_token
    );
}
