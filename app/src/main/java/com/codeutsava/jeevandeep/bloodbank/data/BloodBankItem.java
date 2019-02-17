package com.codeutsava.jeevandeep.bloodbank.data;

public class BloodBankItem {
    private String id;
    private String phoneNumber;
    private String name;
    private String location;
    private String image;
    private String latitude;
    private String longitude;

    public BloodBankItem(String id, String phoneNumber, String name, String location, String image, String latitude, String longitude) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.location = location;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
