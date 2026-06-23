package com.consultoria.proyectos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank
    private String nombreEmpresa;
    @NotNull
    private Long nit;
    @NotBlank
    private String sector;
    @NotBlank
    private String direccion;
    @Email
    @NotNull
    private String emailEmpresa;
}
