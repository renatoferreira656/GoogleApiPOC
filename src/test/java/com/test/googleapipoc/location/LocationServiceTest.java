package com.test.googleapipoc.location;

import com.test.googleapipoc.model.GeoPointStatus;
import com.test.googleapipoc.model.Location;
import com.test.googleapipoc.model.geocode.GeoCodeInfo;
import com.test.googleapipoc.model.geocode.GeoCodeResults;
import com.test.googleapipoc.model.geocode.GeoPoint;
import com.test.googleapipoc.model.geocode.Geometry;
import com.test.googleapipoc.service.HTTPService;
import com.test.googleapipoc.service.LocationService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationServiceTest {

    @Test
    public void testShouldReturnNotFoundWhenGoogleAPIReturnEmptyResults() {
        HTTPService httpService = mock(HTTPService.class);
        when(httpService.get(any(), any(), any())).thenReturn(new GeoCodeResults());

        LocationService locationService = new LocationService();
        locationService.setHTTPService(httpService);
        List<Location> result = locationService.findCoordinatesBy("");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getStatus(), GeoPointStatus.NOT_FOUND);
    }

    @Test
    public void testFindCoordinatesByLocationShouldOneValueWhenGoogleAPIReturnIt() {
        HTTPService httpService = mock(HTTPService.class);
        GeoPoint geoPoint = new GeoPoint().setLat(1.0).setLng(1.0);
        GeoCodeInfo info = new GeoCodeInfo().setFormattedAddress("formattedName").setGeometry(new Geometry().setLocation(geoPoint));
        GeoCodeResults apiResult = new GeoCodeResults().setResults(Collections.singletonList(info));

        when(httpService.get(any(), any(), any())).thenReturn(apiResult);

        LocationService locationService = new LocationService();
        locationService.setHTTPService(httpService);
        List<Location> foundLocations = locationService.findCoordinatesBy("");
        assertEquals(foundLocations.size(), 1);
        Location result = foundLocations.get(0);
        assertEquals(result.getStatus(), GeoPointStatus.FOUND);
        assertEquals(result.getFormattedAddress(), info.getFormattedAddress());
        assertEquals(result.getGeoPoint().getLat(), geoPoint.getLat());
        assertEquals(result.getGeoPoint().getLng(), geoPoint.getLng());
    }

    @Test
    public void testFindCoordinatesByListShouldOneValueWhenGoogleAPIReturnItAnd() {
        HTTPService httpService = mock(HTTPService.class);
        GeoPoint geoPoint = new GeoPoint().setLat(1.0).setLng(1.0);
        GeoCodeInfo info = new GeoCodeInfo().setFormattedAddress("formattedName").setGeometry(new Geometry().setLocation(geoPoint));
        GeoCodeResults apiResult = new GeoCodeResults().setResults(Collections.singletonList(info));

        when(httpService.get(any(), any(), any())).thenReturn(apiResult);

        LocationService locationService = new LocationService();
        locationService.setHTTPService(httpService);
        List<Location> foundLocations = locationService.findCoordinatesBy(Collections.singleton(""));
        assertEquals(foundLocations.size(), 1);
        Location result = foundLocations.get(0);
        assertEquals(result.getStatus(), GeoPointStatus.FOUND);
        assertEquals(result.getFormattedAddress(), info.getFormattedAddress());
        assertEquals(result.getGeoPoint().getLat(), geoPoint.getLat());
        assertEquals(result.getGeoPoint().getLng(), geoPoint.getLng());
    }
}
