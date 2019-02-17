package com.codeutsava.jeevandeep.auth.otpverify.api;


import com.codeutsava.jeevandeep.auth.otpverify.data.OtpVerifyResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OtpVerifyApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_VERIFY_OTP)
    Call<OtpVerifyResponse> getVerifiedOtp(
            @Field("access_token") String access_token,
            @Field("otp") int otp);

}
