package com.codeutsava.jeevandeep.utils.fcm;


import com.codeutsava.jeevandeep.utils.Urls;
import com.codeutsava.jeevandeep.utils.fcm.api.SendFCMApi;
import com.codeutsava.jeevandeep.utils.fcm.model.SendFcmResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSendFCMProvider {

    private SendFCMApi sendFCMApi;
    private Call<SendFcmResponse> sendFcmResponseCall;

    public RetrofitSendFCMProvider() {
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
        sendFCMApi = retrofit.create(SendFCMApi.class);
    }

    public void sendFCM(String accessToken, String fcm, final onSendFCMCallback onSendFCMCallback) {
        sendFcmResponseCall = sendFCMApi.sendFcm(accessToken, fcm);
        sendFcmResponseCall.enqueue(new Callback<SendFcmResponse>() {
            @Override
            public void onResponse(Call<SendFcmResponse> call, Response<SendFcmResponse> response) {
                        onSendFCMCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SendFcmResponse> call, Throwable t) {
                        onSendFCMCallback.onFailure();
                        t.printStackTrace();
            }
        });
    }
}
