package com.test.googleapipoc.model.geocode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoCodeInfo {
    private Geometry geometry;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public GeoCodeInfo setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public GeoCodeInfo setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }
}
