package com.codeutsava.jeevandeep.utils.resendotp.api;


import com.codeutsava.jeevandeep.utils.Urls;
import com.codeutsava.jeevandeep.utils.resendotp.model.RegenerateOtpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegenerateOtpApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_REGENERATE_OTP)
    Call<RegenerateOtpResponse> regenerateOtp(
            @Field("mobile") String mobile
    );
}
