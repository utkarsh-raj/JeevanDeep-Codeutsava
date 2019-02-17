package com.codeutsava.jeevandeep.bloodbank.provider;


import com.codeutsava.jeevandeep.bloodbank.BloodBankCallback;
import com.codeutsava.jeevandeep.bloodbank.api.BloodBankListApi;
import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;
import com.codeutsava.jeevandeep.donationcampaignlist.DonationCampaignCallback;
import com.codeutsava.jeevandeep.donationcampaignlist.api.DonationCampaignListApi;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.donationcampaignlist.provider.DonationCampaignListProvider;
import com.codeutsava.jeevandeep.utils.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBloodBankListProvider implements BloodBankListProvider{

    private BloodBankListApi donorListApi;
    private Call<BloodBankListResponse> DonorListResponseCall;

    public RetrofitBloodBankListProvider() {
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
        donorListApi = retrofit.create(BloodBankListApi.class);
    }

    @Override
    public void getNetworkList(String access_token, BloodBankCallback donorCallback) {
        DonorListResponseCall = donorListApi.getFeedList(access_token);
        DonorListResponseCall.enqueue(new Callback<BloodBankListResponse>() {
            @Override
            public void onResponse(Call<BloodBankListResponse> call, Response<BloodBankListResponse> response) {
                donorCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BloodBankListResponse> call, Throwable t) {
                if(call.isCanceled()){

                }else{
                    donorCallback.onFailed();

                }
            }
        });
    }

    @Override
    public void onCancel() {
        if (DonorListResponseCall!=null)
            DonorListResponseCall.cancel();
    }

}
