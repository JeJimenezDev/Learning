package com.jejimenez;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public Response crearEquipo(@Valid Equipo nuevoEquipo) {
        Equipo guardado = equipoService.guardarEquipo(nuevoEquipo);
        return Response.status(Response.Status.CREATED).entity(guardado).build();
    }

    @GET
    @Path("/buscar")
    public List<Equipo> buscarPorMarca(@QueryParam("marca") String marca) {
        return Equipo.list("marca", marca);
    }

    @GET
    @Path("/equipos/v2") // Le ponemos v2 para comparar
    public List<EquipoDTO> listarEquiposV2() {
        return equipoService.listarEquiposDTO();
    }

    @PUT
    @Path("/equipos/{id}")
    @Transactional
    public Response actualizarEquipo(@PathParam("id") Long id, Equipo datosNuevos) {
        Equipo actualizado = equipoService.actualizarEquipo(id, datosNuevos);
        return Response.ok(actualizado).build();
    }

    @DELETE // este es el que borra por id
    @Path("/equipos/{id}")
    @Transactional
    public Response eliminarEquipo(@PathParam("id") Long id) {
        Equipo equipo = Equipo.findById(id);
        if (equipo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        equipo.delete();
        return Response.noContent().build(); // Devuelve 204 No Content (Éxito sin datos)
    }

    @GET
    @Path("/conteo")
    public long obtenerTotalEquipos() {
        // Panache ya tiene el método count() integrado en la entidad
        return Equipo.count();
    }

    @GET
    @Path("/equipos/paginados")
    public List<Equipo> listarPaginados(@QueryParam("page") int page) {
        // Traemos de a 5 equipos por página
        return Equipo.findAll().page(page, 5).list();
    }
}