package com.jejimenez;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/inventario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventarioResource {

    // --- SECCIÓN DE CATEGORÍAS ---

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

    // --- SECCIÓN DE EQUIPOS ---

    @GET
    @Path("/equipos")
    public List<Equipo> listarEquipos() {
        return Equipo.listAll();
    }

    @POST
    @Path("/equipos")
    @Transactional
    public Response crearEquipo(Equipo nuevoEquipo) {
        // 1. Validación de seguridad: ¿Viene una categoría en el JSON?
        if (nuevoEquipo.categoria == null || nuevoEquipo.categoria.id == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: El equipo debe tener una categoría con un ID válido.")
                    .build();
        }

        // 2. Buscamos si la categoría realmente existe en la base de datos
        Categoria cat = Categoria.findById(nuevoEquipo.categoria.id);
        if (cat == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error: La categoría con ID " + nuevoEquipo.categoria.id + " no existe.")
                    .build();
        }

        // 3. Enlazamos el equipo con la categoría encontrada y guardamos
        nuevoEquipo.categoria = cat;
        nuevoEquipo.persist();

        return Response.status(Response.Status.CREATED).entity(nuevoEquipo).build();
    }
}