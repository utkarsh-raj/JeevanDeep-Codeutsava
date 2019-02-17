package com.codeutsava.jeevandeep.donationcampaignlist.data;

public class DonationCampaignItem {
    private String id;
    private String organiser;
    private String name;
    private String location;
    private String image;
    private String latitude;
    private String longitude;
    private String description;
    private String date;
    private String phoneNumber;

    public DonationCampaignItem(String id, String organiser, String name, String location, String image, String latitude, String longitude, String description, String date, String phoneNumber) {
        this.id = id;
        this.organiser = organiser;
        this.name = name;
        this.location = location;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.date = date;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getOrganiser() {
        return organiser;
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

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
