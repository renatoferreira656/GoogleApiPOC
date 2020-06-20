package com.test.directions.model;

import com.test.directions.model.geocode.GeoPoint;

public class Location {

    private GeoPointStatus status;

    private String formattedAddress;

    private String locationQuery;

    private GeoPoint geoPoint;

    public static Location notFound(String location) {
        Location l = new Location();
        l.locationQuery = location;
        l.status = GeoPointStatus.NOT_FOUND;
        return l;
    }

    public static Location found(String location, String formattedAddress, GeoPoint geoPoint) {
        Location l = new Location();
        l.locationQuery = location;
        l.formattedAddress = formattedAddress;
        l.geoPoint = geoPoint;
        l.status = GeoPointStatus.RESOLVED;
        return l;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public GeoPointStatus getStatus() {
        return status;
    }

    public void setStatus(GeoPointStatus status) {
        this.status = status;
    }

    public String getLocationQuery() {
        return locationQuery;
    }

    public void setLocationQuery(String locationQuery) {
        this.locationQuery = locationQuery;
    }
}
