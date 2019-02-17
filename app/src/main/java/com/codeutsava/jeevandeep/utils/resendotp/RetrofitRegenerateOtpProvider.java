package com.codeutsava.jeevandeep.utils.resendotp;


import com.codeutsava.jeevandeep.utils.Urls;
import com.codeutsava.jeevandeep.utils.resendotp.api.RegenerateOtpApi;
import com.codeutsava.jeevandeep.utils.resendotp.model.RegenerateOtpResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRegenerateOtpProvider {

    private RegenerateOtpApi regenerateOtpApi;
    private Call<RegenerateOtpResponse> regenerateOtpResponseCall;

    public RetrofitRegenerateOtpProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        regenerateOtpApi = retrofit.create(RegenerateOtpApi.class);
    }

    public void regenerateOtp(String mobile, final onRegenerateOtpCallback onSendFCMCallback) {
        regenerateOtpResponseCall = regenerateOtpApi.regenerateOtp(mobile);
        regenerateOtpResponseCall.enqueue(new Callback<RegenerateOtpResponse>() {
            @Override
            public void onResponse(Call<RegenerateOtpResponse> call, Response<RegenerateOtpResponse> response) {
                        onSendFCMCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<RegenerateOtpResponse> call, Throwable t) {
                        onSendFCMCallback.onFailure();
                        t.printStackTrace();
            }
        });
    }
}
