package com.codeutsava.jeevandeep.donorlist.api;

import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DonorListApi {


    @FormUrlEncoded
    @POST(Urls.SUB_URL_DONOR_LIST)
    Call<DonorListResponse> getDonorList(
            @Field("access_token") String access_token
    );
}