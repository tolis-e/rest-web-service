package org.example.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.data.CarRepository;
import org.example.model.Car;
import org.example.services.CarRegistration;

@Path("/carservice")
@RequestScoped
@Stateful
public class CarService {

    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CarRepository repository;

    @Inject
    CarRegistration registration;

    @GET
    @Path("/cars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> listAllCars() {
        return repository.findAllCars();
    }

    @GET
    @Path("/car/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car lookupMemberById(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCar(Car car) {
        Response.ResponseBuilder builder = null;
        try {
            validateCar(car);
            registration.register(car);
            builder = Response.ok().entity(car);
        } catch (ConstraintViolationException ce) {
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("CarNumberFrame", "Exists");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    private void validateCar(Car car) throws ConstraintViolationException, ValidationException {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
        if (carNumberFrameAlreadyExists(car.getNumberFrame())) {
            throw new ValidationException("Unique Car Number Frame Violation");
        }
    }

    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        Map<String, String> responseObj = new HashMap<String, String>();
        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    private boolean carNumberFrameAlreadyExists(String carNumberFrame) {
        Car car = null;
        try {
            car = repository.findByCarNumberFrame(carNumberFrame);
        } catch (NoResultException ignore) {
        }
        return car != null;
    }
}
