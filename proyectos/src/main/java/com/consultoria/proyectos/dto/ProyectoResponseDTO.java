package com.consultoria.proyectos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoResponseDTO {

    private Long id;
    
    private Long clienteId;

    private String nombre;

    private Integer presupuesto;

    private Date fechaInicio;

    private ClienteDTO cliente;


}
