package com.codeutsava.jeevandeep.bloodbankinventory.provider;

import com.codeutsava.jeevandeep.bloodbankinventory.SignupCallBack;
import com.codeutsava.jeevandeep.bloodbankinventory.api.InventoryUpdateApi;
import com.codeutsava.jeevandeep.bloodbankinventory.data.InventoryUpdateResponse;
import com.codeutsava.jeevandeep.utils.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupRetrofitProvider implements SignupProvider {

    private InventoryUpdateApi inventoryUpdateApi;
    private Retrofit retrofit;
    Call<InventoryUpdateResponse> responseCall;


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
        inventoryUpdateApi = retrofit.create(InventoryUpdateApi.class);
    }

    @Override
    public void getSignupRequest(String user_name, String location, String bloodgroup, String access_token, File profile_image, boolean is_imageupdated, final SignupCallBack callBack) {

//        RequestBody username = RequestBody.create( MediaType.parse("multipart/form-data"), user_name);
//        RequestBody userlocation = RequestBody.create( MediaType.parse("multipart/form-data"), location);
//        RequestBody userbloodgroup = RequestBody.create( MediaType.parse("multipart/form-data"), bloodgroup);
//        RequestBody useraccesstoken = RequestBody.create( MediaType.parse("multipart/form-data"), access_token);
//        MultipartBody.Part userimage;

//        if(is_imageupdated){
//            RequestBody imagebody = RequestBody.create(MediaType.parse("multipart/form-data"), profile_image);
//            userimage = MultipartBody.Part.createFormData("profile_image", profile_image.getName(), imagebody);
//        }
//        else{
//            RequestBody imagebody = RequestBody.create(MediaType.parse("multipart/form-data"), "");
//            userimage = MultipartBody.Part.createFormData("profile_image", "", imagebody);
//        }

        responseCall = inventoryUpdateApi.getSignupDetails(user_name, location, bloodgroup, access_token);
        responseCall.enqueue(new Callback<InventoryUpdateResponse>() {
            @Override
            public void onResponse(Call<InventoryUpdateResponse> call, Response<InventoryUpdateResponse> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<InventoryUpdateResponse> call, Throwable t) {
                callBack.onFaiure(t.getMessage());
                t.printStackTrace();

            }
        });


    }
}
