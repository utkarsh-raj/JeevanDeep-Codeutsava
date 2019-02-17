package com.codeutsava.jeevandeep.auth.login.provider;

import android.util.Log;

import com.codeutsava.jeevandeep.auth.login.LoginCallBack;
import com.codeutsava.jeevandeep.auth.login.api.LoginApi;
import com.codeutsava.jeevandeep.auth.login.data.LoginData;
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

import static com.codeutsava.jeevandeep.utils.Urls.*;

public class LoginRetrofitProvider implements LoginProvider {

    private LoginApi loginApi;
    private Retrofit retrofit;


    public LoginRetrofitProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loginApi = retrofit.create(LoginApi.class);
    }

    @Override
    public void requestLogin(String contact_no, final LoginCallBack loginCallBack) {
        Call<LoginData> loginDataCall = loginApi.getLoginDetails(contact_no);
        loginDataCall.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                loginCallBack.onSuccess(response.body());
                Log.d("TEEsst",response.message());
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                t.printStackTrace();
                loginCallBack.onFailure();

            }
        });
    }
}
