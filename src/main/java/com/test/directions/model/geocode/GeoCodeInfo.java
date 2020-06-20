package com.test.directions.model.geocode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoCodeInfo {
    private Geometry geometry;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
