package com.jejimenez;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.LocalDateTime;

@Entity
public class Equipo extends PanacheEntity {

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, message = "La marca debe tener al menos 2 caracteres")
    public String marca;

    @NotBlank(message = "El modelo es obligatorio")
    public String modelo;

    @NotNull(message = "El equipo debe tener una categoría")
    @ManyToOne
    public Categoria categoria;

    @Column(name = "fecha_creacion", updatable = false)
    public LocalDateTime fechaCreacion;
}