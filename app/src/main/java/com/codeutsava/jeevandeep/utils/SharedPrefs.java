package com.codeutsava.jeevandeep.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String PREF_NAME = "welcome";
    private static final String KEY_LOGIN_OUR_APP = "UserIdLoggedInTToOurApp";
    private static final String KEY_FIRST_LAUNCH = "FirstLaunch";
    private static final String KEY_LOGIN_SKIPPED = "login_skipped";
    private static final String KEY_ACCESS_TOKEN = "token";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_RESTAURANT_TOKEN = "restaurantToken";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_HOUSE_NO= "house_no";
    private static final String KEY_LOCALITY = "locality";
    private static final String KEY_COLONY = "colony";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_lOCATION_POSITION = "locationposition";
    private static final String KEY_CITY_POSITION = "cityposition";



    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLoggedInOurApp() {
        return pref.getBoolean(KEY_LOGIN_OUR_APP, false);
    }
    public void setLoggedInOurApp(boolean loggedIn) {
        editor.putBoolean(KEY_LOGIN_OUR_APP, loggedIn);
        editor.commit();
    }

    public boolean isFirstLaunch() {
        return pref.getBoolean(KEY_FIRST_LAUNCH, true);
    }
    public void setFirstLaunch(boolean firstLaunch) {
        editor.putBoolean(KEY_FIRST_LAUNCH, firstLaunch);
        editor.commit();
    }

    public boolean isLoggedSkipped() {
        return pref.getBoolean(KEY_LOGIN_SKIPPED, false);
    }
    public void setLoginSkipped(boolean loggedIn) {
        editor.putBoolean(KEY_LOGIN_SKIPPED, loggedIn);
        editor.commit();
    }

    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, "1");
    }
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, "1");
    }
    public void setMobileNumber(String mobnumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobnumber);
        editor.commit();
    }

    public String getRestaurantToken() {
        return pref.getString(KEY_RESTAURANT_TOKEN, "0");
    }
    public void setRestaurantTokenToken(String restaurantToken) {
        editor.putString(KEY_RESTAURANT_TOKEN, restaurantToken);
        editor.commit();
    }

    public String getLatitude(){
        return pref.getString(KEY_LATITUDE,"1");
    }

    public void setLatitude(String latitude){
        editor.putString(KEY_LATITUDE,latitude);
        editor.apply();
    }

    public String getLongitude(){
        return pref.getString(KEY_LONGITUDE,"1");
    }

    public void setLongitude(String longitude){
        editor.putString(KEY_LONGITUDE,longitude);
        editor.apply();
    }


    public String getHouseNo(){
        return pref.getString(KEY_HOUSE_NO,"1");
    }
    public void setHouseNo(String colony){
        editor.putString(KEY_HOUSE_NO,colony);
        editor.apply();
    }

    public String getLocality(){
        return pref.getString(KEY_LOCALITY,"1");
    }
    public void setLocality(String colony){
        editor.putString(KEY_LOCALITY,colony);
        editor.apply();
    }

    public String getColonyName(){
        return pref.getString(KEY_COLONY,"1");
    }
    public void setColonyName(String colony){
        editor.putString(KEY_COLONY,colony);
        editor.apply();
    }

    public String getCity(){
        return pref.getString(KEY_CITY,"1");
    }
    public void setCity(String city){
        editor.putString(KEY_CITY,city);
        editor.apply();
    }

    public String getState(){
        return pref.getString(KEY_STATE,"1");
    }
    public void setState(String state){
        editor.putString(KEY_STATE,state);
        editor.apply();
    }

    public String getLocationPosition(){
        return pref.getString(KEY_lOCATION_POSITION,"-1");
    }
    public void setLocationPosition(String locationPosition){
        editor.putString(KEY_lOCATION_POSITION,locationPosition);
        editor.apply();
    }

    public String getCityPosition(){
        return pref.getString(KEY_CITY_POSITION,"-1");
    }
    public void setCityPosition(String cityPosition){
        editor.putString(KEY_CITY_POSITION,cityPosition);
        editor.apply();
    }

}
