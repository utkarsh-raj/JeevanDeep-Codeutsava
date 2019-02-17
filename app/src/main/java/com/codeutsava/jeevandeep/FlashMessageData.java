package com.codeutsava.jeevandeep;

public class FlashMessageData {
    private String user_name;
    private String user_mobile;
    private String user_location;
    private String user_bloodgroup;

    public FlashMessageData(String user_name, String user_mobile, String user_location, String user_bloodgroup) {
        this.user_name = user_name;
        this.user_mobile = user_mobile;
        this.user_location = user_location;
        this.user_bloodgroup = user_bloodgroup;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public String getUser_location() {
        return user_location;
    }

    public String getUser_bloodgroup() {
        return user_bloodgroup;
    }
}
