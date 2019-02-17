package com.codeutsava.jeevandeep.donationcampaignlist.provider;


import com.codeutsava.jeevandeep.donationcampaignlist.DonationCampaignCallback;
import com.codeutsava.jeevandeep.donationcampaignlist.api.DonationCampaignListApi;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.donorlist.DonorCallback;
import com.codeutsava.jeevandeep.donorlist.api.DonorListApi;
import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;
import com.codeutsava.jeevandeep.donorlist.provider.DonorListProvider;
import com.codeutsava.jeevandeep.utils.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDonationCampaignListProvider implements DonationCampaignListProvider{

    private DonationCampaignListApi donorListApi;
    private Call<DonationCampaignListResponse> DonorListResponseCall;

    public RetrofitDonationCampaignListProvider() {
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
        donorListApi = retrofit.create(DonationCampaignListApi.class);
    }

    @Override
    public void getNetworkList(String access_token, DonationCampaignCallback donorCallback) {
        DonorListResponseCall = donorListApi.getFeedList(access_token);
        DonorListResponseCall.enqueue(new Callback<DonationCampaignListResponse>() {
            @Override
            public void onResponse(Call<DonationCampaignListResponse> call, Response<DonationCampaignListResponse> response) {
                donorCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DonationCampaignListResponse> call, Throwable t) {
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
