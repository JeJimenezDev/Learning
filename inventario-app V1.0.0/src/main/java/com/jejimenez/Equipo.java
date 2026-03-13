package com.jejimenez;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Equipo extends PanacheEntity {
    @NotBlank(message = "La marca no puede estar vacía")
    public String marca;

    @NotBlank(message = "El modelo es obligatorio")
    public String modelo;

    @ManyToOne // Muchos equipos pertenecen a una sola categoría
    public Categoria categoria;
}