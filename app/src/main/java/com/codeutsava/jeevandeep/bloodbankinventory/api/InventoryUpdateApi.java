package com.codeutsava.jeevandeep.bloodbankinventory.api;



import com.codeutsava.jeevandeep.bloodbankinventory.data.InventoryUpdateResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InventoryUpdateApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_SIGNUP)
    Call<InventoryUpdateResponse> getSignupDetails(
            @Field("name") String username,
            @Field("location") String userlocation,
            @Field("blood_group") String userbloodgroup,
            @Field("access_token") String useraccesstoken);


}
