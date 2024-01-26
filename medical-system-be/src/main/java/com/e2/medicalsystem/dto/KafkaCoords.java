package com.e2.medicalsystem.dto;

public class KafkaCoords {
    public float lat;
    public float lng;
    public String user;

    public KafkaCoords(float lat, float lng, String user) {
        this.lat = lat;
        this.lng = lng;
        this.user = user;
    }

    public KafkaCoords(){

    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
