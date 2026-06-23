package com.consultoria.proyectos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultoria.proyectos.dto.ProyectoDTO;
import com.consultoria.proyectos.dto.ProyectoResponseDTO;
import com.consultoria.proyectos.service.ProyectoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/proyectos")
@Tag(name = "Proyectos", description = "Operaciones sobre Proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService servi;

    @Operation(summary = "Busca todos los proyectos", description = "Retorna todos los proyectos registrados")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyectos encontrados"),
        @ApiResponse(responseCode = "244", description = "No hay contenido de proyectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ProyectoResponseDTO>> listar(){
        List<ProyectoResponseDTO> si = servi.listar();
        if(si.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(si);
    }

    @Operation(summary = "Guardar proyecto", description = "Guarda los proyectos si los datos ingresados son correctos")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto guardado con éxito"),
        @ApiResponse(responseCode = "404", description = "No se pudo guardar debido a datos incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ProyectoDTO> guardar(@Valid @RequestBody ProyectoDTO dto){
        try {
            ProyectoDTO gu = servi.guardar(dto);
            return ResponseEntity.ok(gu);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Busca proyectos por ID de Cliente", description = "Retorna los proyectos asociados a un cliente específico")
    @GetMapping("/cliente/{clienteId}") // Corregido agregando la barra '/' faltante
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyectos del cliente encontrados"),
        @ApiResponse(responseCode = "404", description = "Proyectos o cliente no encontrados"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ProyectoResponseDTO>> buscar(
        @PathVariable 
        @Parameter(description = "ID del Cliente", required = true, examples = @ExampleObject(value = "1")) Long clienteId){
        try {
            List<ProyectoResponseDTO> dto = servi.buscarxId(clienteId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Otorga datos de proyecto por id", description = "Busca y retorna los datos básicos de un proyecto según su id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proyecto encontrado"),
        @ApiResponse(responseCode = "404", description = "Proyecto no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<ProyectoResponseDTO> obtenerProyecto(
        @PathVariable 
        @Parameter(description = "ID del Proyecto", required = true, examples = @ExampleObject(value = "1")) Long id) {
        try {
            ProyectoResponseDTO dto = servi.buscarProyectoPorIdSimple(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}