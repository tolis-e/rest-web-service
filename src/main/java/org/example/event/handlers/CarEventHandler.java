package org.example.event.handlers;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.example.event.types.RegistrationEvent;
import org.example.model.Car;
import org.example.utils.StringUtilities;

public class CarEventHandler {

    @Inject
    private Logger log;

    public void onRegistration(@Observes @RegistrationEvent Car car) {
        log.info(StringUtilities.concatStrings("Registration Event received for car: ", car.toString()));
    }

}
