package com.jejimenez;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/inventario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventarioResource {

    @Inject
    EquipoService equipoService; // Inyección al inicio

    @GET
    @Path("/categorias")
    public List<Categoria> listarCategorias() {
        return Categoria.listAll();
    }

    @POST
    @Path("/categorias")
    @Transactional
    public Response crearCategoria(Categoria categoria) {
        categoria.persist();
        return Response.status(Response.Status.CREATED).entity(categoria).build();
    }

    @GET
    @Path("/equipos")
    public List<Equipo> listarEquipos() {
        return Equipo.listAll();
    }

    @POST
    @Path("/equipos")
    public Response crearEquipo(Equipo nuevoEquipo) {
        // Toda la lógica de validación de categoría debe estar DENTRO de guardarEquipo
        Equipo guardado = equipoService.guardarEquipo(nuevoEquipo);
        return Response.status(Response.Status.CREATED).entity(guardado).build();
    }
}