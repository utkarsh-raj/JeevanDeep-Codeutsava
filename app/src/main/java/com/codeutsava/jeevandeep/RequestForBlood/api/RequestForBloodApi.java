package com.codeutsava.jeevandeep.RequestForBlood.api;


import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RequestForBloodApi {

    @Multipart
    @POST(Urls.SUB_URL_REQUEST_BLOOD)
    Call<RequestForBloodResponse> getSignupDetails(
            @Part("name") RequestBody username,
            @Part("phoneNumber") RequestBody contactno,
            @Part("treatment") RequestBody usertreatment,
            @Part("hospital") RequestBody userhospital,
            @Part("blood_group") RequestBody userbloodgroup,
            @Part("number_of_units") RequestBody usernumberofunits,
            @Part("age") RequestBody userage,
            @Part("access_token") RequestBody useraccesstoken
    );
}
