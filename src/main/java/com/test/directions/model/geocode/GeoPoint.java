package com.test.directions.model.geocode;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoPoint geoPoint = (GeoPoint) o;
        return Objects.equals(lat, geoPoint.lat) &&
                Objects.equals(lng, geoPoint.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
