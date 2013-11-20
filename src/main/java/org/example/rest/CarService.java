package org.example.rest;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
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
public class CarService extends AbstractRestEndpoint {

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
            validateModel(car);
            registration.register(car);
            builder = Response.ok().entity(car);
        } catch (ConstraintViolationException ce) {
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            builder = createExceptionResponse(e);
        }
        return builder.build();
    }
}
