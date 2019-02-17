package com.codeutsava.jeevandeep.utils;


public class Urls {

//    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String BASE_URL ="http://172.22.124.179:8000/"; //staging server


    // On-boarding
    public static final String SUB_URL_SPLASH_SCREEN = "splash/";
    public static final String SUB_URL_WELCOME_SCREEN = "welcome/";

    // Authentication
    public static final String SUB_URL_LOGIN = "auth/user_signup";
    public static final String SUB_URL_SIGNUP = "signup/";
    public static final String SUB_URL_VERIFY_OTP ="auth/user_signup/otp";
    public static final String SUB_URL_FORGOT_PASSWORD ="forgot_password/";
    public static final String SUB_URL_FORGOT_OTP_PASSWORD ="forgot_password/";

    // Profile
    public static final String SUB_URL_EDIT_PROFILE = "edit_profile/";
    public static final String SUB_URL_UPDATE_PROFILE_PIC = "update_profile_pic/";
    public static final String SUB_URL_USER_PROFILE = "user_profile/";

    // Home
    public static final String SUB_URL_NETWORK_LIST = "network/";
//    public static final String SUB_URL_FEED_LIST = "news_feed/";
    public static final String SUB_URL_FEED_LIST = "get_news/";



    public static final String SUB_URL_DONOR_LIST= "req/";
    public static final String SUB_URL_BLOOD_BANK_LIST = "bank/req/";
    public static final String SUB_URL_DONATION_CAMPAIGN_LIST = "bank/campaign/req";
    public static final String SUB_URL_REQUEST_BLOOD = "request_blood/";
    public static final String SUB_URL_GROUP_DETAILS = "group_chat/get_group_details/";

    // Group Chat
    public static final String SUB_URL_GROUP_CHAT_SEND_MESSAGE = "group_chat/send_message/";
    public static final String SUB_URL_GROUP_CHAT_LIST = "group_chat/list/";
    public static final String SUB_URL_GROUP_CHAT_MESSAGE_DELETE = "group_chat/delete_message/";

    //Firebase
    public static final String SUB_URL_FCM = "fcm/";

    //Logout
    public static final String SUB_URL_LOGOUT = "logout/";


    public static final String SUB_URL_REGENERATE_OTP = "regenerate_otp/";
}

