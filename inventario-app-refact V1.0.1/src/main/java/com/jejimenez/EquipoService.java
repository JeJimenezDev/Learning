package com.jejimenez;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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
}