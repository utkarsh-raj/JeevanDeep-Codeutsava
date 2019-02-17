package com.codeutsava.jeevandeep.auth.otpverify.provider;

import android.util.Log;

import com.codeutsava.jeevandeep.auth.otpverify.api.OtpVerifyApi;
import com.codeutsava.jeevandeep.auth.otpverify.data.OtpVerifyResponse;
import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyCallback;
import com.codeutsava.jeevandeep.utils.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpverifyRetrofitProvider implements OtpVerifyProvider {

    private OtpVerifyApi verifyApi;
    private Retrofit retrofit;

    public OtpverifyRetrofitProvider() {

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
        verifyApi = retrofit.create(OtpVerifyApi.class);
    }

    @Override
    public void verifyOtp(String contactNo, int otp, final OtpVerifyCallback otpVerifyCallBack) {

        Call<OtpVerifyResponse> call = verifyApi.getVerifiedOtp(contactNo, otp);
        call.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                otpVerifyCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                otpVerifyCallBack.onFailure(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onFailed(String message) {
        Log.d("", message);

    }
}
