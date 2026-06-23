package com.consultoria.proyectos.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDTO {

    private Long id;

    private Long clienteId;
    @NotBlank
    private String nombre;
    @NotNull
    private Integer presupuesto;
    @NotNull
    private Date fechaInicio;


}
