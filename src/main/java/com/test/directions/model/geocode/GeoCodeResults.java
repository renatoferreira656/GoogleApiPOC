package com.test.directions.model.geocode;

import java.util.List;

public class GeoCodeResults {
        private List<GeoCodeInfo> results;

        public List<GeoCodeInfo> getResults() {
                return results;
        }

        public GeoCodeResults setResults(List<GeoCodeInfo> results) {
                this.results = results;
                return this;
        }
}
