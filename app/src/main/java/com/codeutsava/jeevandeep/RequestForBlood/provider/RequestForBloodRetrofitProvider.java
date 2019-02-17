package com.codeutsava.jeevandeep.RequestForBlood.provider;

import com.codeutsava.jeevandeep.RequestForBlood.RequestForBloodCallBack;
import com.codeutsava.jeevandeep.RequestForBlood.api.RequestForBloodApi;
import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;
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

public class RequestForBloodRetrofitProvider implements RequestForBloodProvider {

    private RequestForBloodApi signupApi;
    private Retrofit retrofit;


    public RequestForBloodRetrofitProvider() {

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
        signupApi = retrofit.create(RequestForBloodApi.class);
    }

    @Override
    public void getSignupRequest(String user_name, String mobile_number, String treatment, String hospital, String bloodgroup, String age, String number_of_units, String access_token, final RequestForBloodCallBack callBack) {

        RequestBody username = RequestBody.create( MediaType.parse("multipart/form-data"), user_name);
        RequestBody contactno = RequestBody.create( MediaType.parse("multipart/form-data"), mobile_number);
        RequestBody usertreatment = RequestBody.create( MediaType.parse("multipart/form-data"), treatment);
        RequestBody userhospital = RequestBody.create( MediaType.parse("multipart/form-data"), hospital);
        RequestBody userbloodgroup = RequestBody.create( MediaType.parse("multipart/form-data"), bloodgroup);
        RequestBody usernumberofunits = RequestBody.create( MediaType.parse("multipart/form-data"), number_of_units);
        RequestBody userage = RequestBody.create( MediaType.parse("multipart/form-data"), age);
        RequestBody useraccesstoken = RequestBody.create( MediaType.parse("multipart/form-data"), access_token);


        Call<RequestForBloodResponse> responseCall = signupApi.getSignupDetails(username, contactno, usertreatment, userhospital, userbloodgroup, usernumberofunits, userage, useraccesstoken);
        responseCall.enqueue(new Callback<RequestForBloodResponse>() {
            @Override
            public void onResponse(Call<RequestForBloodResponse> call, Response<RequestForBloodResponse> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RequestForBloodResponse> call, Throwable t) {
                callBack.onFaiure(t.getMessage());
                t.printStackTrace();

            }
        });


    }
}
