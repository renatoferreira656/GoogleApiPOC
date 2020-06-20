package com.test.directions.model.geocode;

public class GeoPoint {
    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public GeoPoint setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public GeoPoint setLng(Double lng) {
        this.lng = lng;
        return this;
    }
}
