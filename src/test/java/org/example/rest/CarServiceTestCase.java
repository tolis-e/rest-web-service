package org.example.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.example.model.Car;
import org.junit.Test;

public class CarServiceTestCase extends CarServiceTestCaseStructure {

    @Inject
    CarService carService;

    @Test
    public void testRegister() throws Exception {
        Car car = new Car("X12345", "Arquillian", "Black");
        Response response = carService.addCar(car);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    public void testEmptyNumberFrame() throws Exception {
        Car car = new Car(null, "Arquillian", "Black");
        Response response = carService.addCar(car);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }
}
