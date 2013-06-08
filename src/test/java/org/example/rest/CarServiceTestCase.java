package org.example.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.example.model.Car;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

public class CarServiceTestCase extends CarServiceTestCaseStructure {

    @Inject
    CarService carService;

    @Test
    @InSequence(1)
    public void testRegister() throws Exception {
        Car car = new Car("X12345", "Arquillian", "Black");
        Response response = carService.addCar(car);
        assertEquals("Unexpected response status", 200, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void testDuplicateNumberFrame() throws Exception {
        Car car = new Car("X123456", "Arquillian", "Black");
        Response response = carService.addCar(car);
        assertEquals("Unexpected response status", 200, response.getStatus());

        Car car_ = new Car("X123456", "Arquillian_", "Black_");
        Response response_ = carService.addCar(car_);

        assertEquals("Response status 409", 409, response_.getStatus());
        assertNotNull("Entity not null", response_.getEntity());
    }
}
