package com.codeutsava.jeevandeep.profile.provider;

import com.codeutsava.jeevandeep.profile.SignupCallBack;
import com.codeutsava.jeevandeep.profile.api.SignupApi;
import com.codeutsava.jeevandeep.profile.data.SignupResponse;
import com.codeutsava.jeevandeep.utils.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupRetrofitProvider implements SignupProvider {

    private SignupApi signupApi;
    private Retrofit retrofit;


    public SignupRetrofitProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        signupApi = retrofit.create(SignupApi.class);
    }

    @Override
    public void getSignupRequest(String user_name, String location, String bloodgroup, File profile_image, boolean is_imageupdated, final SignupCallBack callBack) {

        RequestBody username = RequestBody.create( MediaType.parse("multipart/form-data"), user_name);
        RequestBody userlocation = RequestBody.create( MediaType.parse("multipart/form-data"), location);
        RequestBody userbloodgroup = RequestBody.create( MediaType.parse("multipart/form-data"), bloodgroup);
        MultipartBody.Part userimage;

        if(is_imageupdated){
            RequestBody imagebody = RequestBody.create(MediaType.parse("multipart/form-data"), profile_image);
            userimage = MultipartBody.Part.createFormData("profile_image", profile_image.getName(), imagebody);
        }
        else{
            RequestBody imagebody = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            userimage = MultipartBody.Part.createFormData("profile_image", "", imagebody);
        }

        Call<SignupResponse> responseCall = signupApi.getSignupDetails(username, userlocation, userbloodgroup, userimage);
        responseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                callBack.onFaiure(t.getMessage());
                t.printStackTrace();

            }
        });


    }
}
