package com.test.directions.model.geocode;

public class Geometry {

    private GeoPoint location;

    public GeoPoint getLocation() {
        return location;
    }

    public Geometry setLocation(GeoPoint location) {
        this.location = location;
        return this;
    }
}
