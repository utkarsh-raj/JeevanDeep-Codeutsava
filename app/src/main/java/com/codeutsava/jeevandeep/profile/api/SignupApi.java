package com.codeutsava.jeevandeep.profile.api;



import com.codeutsava.jeevandeep.profile.data.SignupResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SignupApi {

    @Multipart
    @POST(Urls.SUB_URL_SIGNUP)
    Call<SignupResponse> getSignupDetails(
            @Part("user_name") RequestBody username,
            @Part("location") RequestBody userlocation,
            @Part("blood_group") RequestBody userbloodgroup,
            @Part MultipartBody.Part userimage);
}
