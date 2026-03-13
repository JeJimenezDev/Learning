package com.jejimenez;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Categoria extends PanacheEntity {
    public String nombre;

    // Una categoría tiene muchos equipos
    @OneToMany(mappedBy = "categoria")
    public List<Equipo> equipos = new ArrayList<>();
}