package org.example.event.handlers;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.example.event.types.RegistrationEvent;
import org.example.model.Car;

public class CarEventHandler {

    @Inject
    private Logger logger;

    public void onRegistration(@Observes @RegistrationEvent Car car) {
        logger.finest(String.format("Registration Event received for car: %s", car.toString()));
    }

}
