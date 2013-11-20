package org.example.rest;

import org.example.data.CarRepository;
import org.example.event.handlers.CarEventHandler;
import org.example.event.types.RegistrationEvent;
import org.example.model.Car;
import org.example.model.Car_;
import org.example.services.CarRegistration;
import org.example.utils.AppResources;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class Deployments {

    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(Car.class, Car_.class, CarService.class, CarRepository.class, CarRegistration.class,
                        AppResources.class, AbstractRestEndpoint.class, CarServiceTestCaseStructure.class, Deployments.class,
                        CarEventHandler.class, RegistrationEvent.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("arquillian-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
