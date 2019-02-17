package com.codeutsava.jeevandeep.auth.bloodbanklogin.api;


import com.codeutsava.jeevandeep.auth.bloodbanklogin.data.LoginData;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_LOGIN)
    Call<LoginData> getLoginDetails(
            @Field("phoneNumber") String contact_no,
            @Field("password") String password);

}
