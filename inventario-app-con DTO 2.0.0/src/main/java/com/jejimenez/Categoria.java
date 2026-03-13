package com.jejimenez;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria extends PanacheEntity {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    public String nombre;
}