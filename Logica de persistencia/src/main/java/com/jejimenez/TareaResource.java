package com.jejimenez;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tareas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TareaResource {

    @GET
    public List<Tarea> listar() {
        return Tarea.listAll();
    }

    @POST
    @Transactional
    public Response agregar(@Valid Tarea nuevaTarea) {
        nuevaTarea.persist();
        return Response.status(Response.Status.CREATED).entity(nuevaTarea).build();
    }

    @PATCH
    @Path("/{id}/completar")
    @Transactional
    public Tarea completar(@PathParam("id") Long id) {
        Tarea tarea = Tarea.findById(id);
        if (tarea == null) {
            throw new NotFoundException("Tarea con ID " + id + " no encontrada");
        }
        tarea.terminada = true;
        return tarea;
    }

    @DELETE

    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        Tarea tarea = Tarea.findById(id);
        if (tarea == null) {
            throw new NotFoundException("Tarea con ID " + id + " no encontrada");
        }
        tarea.delete();
        return Response.noContent().build();
    }
}
