package org.example.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

public abstract class AbstractRestEndpoint {

    @Inject
    private Validator validator;

    protected void validateModel(Object model) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(model);

        if (violations != null && !violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }

    protected Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        Map<String, String> responseObj = new HashMap<String, String>();
        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    protected Response.ResponseBuilder createExceptionResponse(Exception e) {
        Map<String, String> responseObj = new HashMap<String, String>();
        responseObj.put("error", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
}
