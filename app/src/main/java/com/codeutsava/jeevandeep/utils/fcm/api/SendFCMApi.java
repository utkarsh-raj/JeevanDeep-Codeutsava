package com.codeutsava.jeevandeep.utils.fcm.api;


import com.codeutsava.jeevandeep.utils.Urls;
import com.codeutsava.jeevandeep.utils.fcm.model.SendFcmResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendFCMApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_FCM)
    Call<SendFcmResponse> sendFcm(
            @Field("access_token") String access_token,
            @Field("fcm") String fcm
    );
}
