package com.e2.medicalsystem.dto;

public class LatLng {

    public float lat;
    public float lng;

    public LatLng(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng() {
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

}
