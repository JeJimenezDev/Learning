package com.jejimenez;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ValidacionExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        // Recolectamos todos los mensajes de error (ej: "La marca es corta", "El modelo
        // falta")
        String mensajes = exception.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.joining(", "));

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Validación fallida\", \"detalles\": \"" + mensajes + "\"}")
                .build();
    }
}