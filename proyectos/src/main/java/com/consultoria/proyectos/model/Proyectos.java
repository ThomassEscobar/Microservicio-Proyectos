package com.consultoria.proyectos.model;
//Model (Proyecto): id, nombre, clienteId, presupuesto, fechaInicio.

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="proyectos")
public class Proyectos {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    private Long clienteId;

    @NotBlank
    private String nombre;

    @NotNull
    private Integer presupuesto;

    @NotNull
    private Date fechaInicio;


}
