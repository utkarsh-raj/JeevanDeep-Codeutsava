package com.codeutsava.jeevandeep.donationcampaignlist.api;

import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.utils.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DonationCampaignListApi {

    @FormUrlEncoded
    @POST(Urls.SUB_URL_DONATION_CAMPAIGN_LIST)
    Call<DonationCampaignListResponse> getFeedList(
            @Field("access_token") String access_token
    );
}