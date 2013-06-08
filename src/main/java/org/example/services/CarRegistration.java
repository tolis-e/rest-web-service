package org.example.services;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.example.event.types.RegistrationEvent;
import org.example.model.Car;

@Stateless
public class CarRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    @RegistrationEvent
    private Event<Car> carEvent;

    public void register(Car car) throws Exception {
        em.persist(car);
        carEvent.fire(car);
    }
}
