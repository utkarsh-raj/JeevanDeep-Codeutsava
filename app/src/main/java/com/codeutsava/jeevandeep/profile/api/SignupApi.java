package com.codeutsava.jeevandeep.profile.api;



import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.profile.data.SignupResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface SignupApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_SIGNUP)
    Call<SignupResponse> getSignupDetails(
            @Field("name") String username,
            @Field("location") String userlocation,
            @Field("blood_group") String userbloodgroup,
            @Field("access_token") String useraccesstoken);


}
