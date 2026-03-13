package com.jejimenez;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/personalizado/{nombre}")
    @Produces(MediaType.TEXT_PLAIN)
    public String saludo(String nombre) {
        return "Hola " + nombre + ", este es mi primer servicio dinámico en Quarkus";
    }
}