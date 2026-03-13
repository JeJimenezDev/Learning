package com.jejimenez;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tarea extends PanacheEntity {
    @NotBlank(message = "La descripción no puede estar vacía")
    public String descripcion;
    public boolean terminada;
}