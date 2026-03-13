package com.jejimenez;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped // Esto hace que Quarkus maneje esta clase automáticamente
public class EquipoService {

    @Transactional
    public Equipo guardarEquipo(Equipo equipo) {
        if (equipo.categoria == null || equipo.categoria.id == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }

        Categoria cat = Categoria.findById(equipo.categoria.id);
        if (cat == null) {
            throw new NotFoundException("La categoría con ID " + equipo.categoria.id + " no existe");
        }

        equipo.categoria = cat;
        equipo.persist();
        return equipo;
    }

    @Transactional
    public Equipo actualizarEquipo(Long id, Equipo datosNuevos) {
        Equipo entidad = Equipo.findById(id);
        if (entidad == null) {
            throw new NotFoundException("Equipo no encontrado");
        }

        // Actualizamos los campos
        entidad.marca = datosNuevos.marca;
        entidad.modelo = datosNuevos.modelo;

        // Si viene una categoría nueva, la validamos y cambiamos
        if (datosNuevos.categoria != null && datosNuevos.categoria.id != null) {
            Categoria cat = Categoria.findById(datosNuevos.categoria.id);
            if (cat != null)
                entidad.categoria = cat;
        }

        return entidad;
    }

    public List<EquipoDTO> listarEquiposDTO() {
        List<Equipo> equipos = Equipo.listAll();
        return equipos.stream().map(e -> {
            EquipoDTO dto = new EquipoDTO();
            dto.id = e.id;
            dto.marca = e.marca;
            dto.modelo = e.modelo;
            dto.nombreCategoria = (e.categoria != null) ? e.categoria.nombre : "Sin categoría";
            return dto;
        }).toList();
    }

}