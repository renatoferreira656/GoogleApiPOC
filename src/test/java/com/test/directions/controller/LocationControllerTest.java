package com.test.directions.controller;

import com.test.directions.http.BadRequestException;
import com.test.directions.http.NotFoundException;
import com.test.directions.model.Location;
import com.test.directions.model.geocode.GeoCodeInfo;
import com.test.directions.model.geocode.GeoCodeResults;
import com.test.directions.model.geocode.GeoPoint;
import com.test.directions.model.geocode.Geometry;
import com.test.directions.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationControllerTest {

    @Test
    public void testCoordinatesWithTextShouldReturnNotFoundWhenServiceReturnEmptyList(){
        LocationService service= mock(LocationService.class);
        when(service.findCoordinatesBy("")).thenReturn(Collections.emptyList());
        LocationController controller = new LocationController();
        controller.setLocationService(service);
        Assertions.assertThrows(NotFoundException.class, () -> {
            controller.coordinates("path");
        });
    }


    @Test
    public void testCoordinatesWithMultipartShouldReturnNotFoundWhenServiceReturnEmptyList(){
        String content = "line1\nline2\nline3";
        MultipartFile file = new MockMultipartFile("name", content.getBytes());

        LocationService service= mock(LocationService.class);
        when(service.findCoordinatesBy(Collections.singleton(""))).thenReturn(Collections.emptyList());
        LocationController controller = new LocationController();
        controller.setLocationService(service);
        Assertions.assertThrows(NotFoundException.class, () -> {
            controller.coordinates(file);
        });
    }

    @Test
    public void testCoordinatesWithMultipartShouldReturnBadRequestWhenFileIsEmpty(){
        String content = "";
        MultipartFile file = new MockMultipartFile("name", content.getBytes());
        LocationController controller = new LocationController();
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.coordinates(file);
        });
    }

    @Test
    public void testCoordinatesWithMultipartShouldReturnResultWhenServiceReturnResults(){
        String content = "line1\nline2\nline3";
        MultipartFile file = new MockMultipartFile("name", content.getBytes());

        LocationService service= mock(LocationService.class);
        Location expected = Location.found("location", "formatted", new GeoPoint().setLat(1.0).setLng(1.0));


        Set<String> params = new HashSet<>();
        params.add("line1");
        params.add("line2");
        params.add("line3");
        when(service.findCoordinatesBy(params)).thenReturn(Collections.singletonList(expected));

        LocationController controller = new LocationController();
        controller.setLocationService(service);
        List<Location> results = controller.coordinates(file);
        assertEquals(1, results.size());
        assertEquals(expected.getGeoPoint(), results.get(0).getGeoPoint());
        assertEquals(expected.getFormattedAddress(), results.get(0).getFormattedAddress());
    }

    @Test
    public void testCoordinatesWithTextShouldReturnResultWhenServiceReturnResults(){
        LocationService service= mock(LocationService.class);
        Location expected = Location.found("location", "formatted", new GeoPoint().setLat(1.0).setLng(1.0));
        when(service.findCoordinatesBy("location")).thenReturn(Collections.singletonList(expected));
        LocationController controller = new LocationController();
        controller.setLocationService(service);
        List<Location> results = controller.coordinates("location");
        assertEquals(1, results.size());
        assertEquals(expected.getGeoPoint(), results.get(0).getGeoPoint());
        assertEquals(expected.getFormattedAddress(), results.get(0).getFormattedAddress());
    }
}
