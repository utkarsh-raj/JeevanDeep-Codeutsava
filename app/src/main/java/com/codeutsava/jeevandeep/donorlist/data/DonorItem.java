package com.codeutsava.jeevandeep.donorlist.data;

public class DonorItem {
    private String id;
    private String phoneNumber;
    private String name;
    private String location;
    private String blood_group;
    private String number_of_units;
    private String treatment;
    private String date;
    private String latitude;
    private String longitude;
    private String reputation;
    private String verified;

    public DonorItem(String id, String phoneNumber, String name, String location, String blood_group, String number_of_units, String treatment, String date, String latitude, String longitude, String reputation, String verified) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.location = location;
        this.blood_group = blood_group;
        this.number_of_units = number_of_units;
        this.treatment = treatment;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reputation = reputation;
        this.verified = verified;
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

    public String getBlood_group() {
        return blood_group;
    }

    public String getNumber_of_units() {
        return number_of_units;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getReputation() {
        return reputation;
    }

    public String getVerified() {
        return verified;
    }
}
