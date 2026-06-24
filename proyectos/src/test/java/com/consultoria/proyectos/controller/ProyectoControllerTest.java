package com.consultoria.proyectos.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.consultoria.proyectos.dto.ClienteDTO;
import com.consultoria.proyectos.dto.ProyectoDTO;
import com.consultoria.proyectos.dto.ProyectoResponseDTO;
import com.consultoria.proyectos.service.ProyectoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProyectoController.class)
class ProyectoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProyectoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private ProyectoDTO proyectoDTO;
    private ProyectoResponseDTO proyectoResponseDTO;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        Date fecha = new Date();

        clienteDTO = new ClienteDTO(
                1L,
                "Empresa Test",
                12345678L,
                "Tecnologia",
                "Santiago",
                "empresa@test.com"
        );

        proyectoDTO = new ProyectoDTO(
                1L,
                1L,
                "Proyecto Test",
                500000,
                fecha
        );

        proyectoResponseDTO = new ProyectoResponseDTO(
                1L,
                1L,
                "Proyecto Test",
                500000,
                fecha,
                clienteDTO
        );
    }

    @Test
    void listar_ok() throws Exception {
        when(service.listar()).thenReturn(Arrays.asList(proyectoResponseDTO));

        mockMvc.perform(get("/proyectos"))
                .andExpect(status().isOk());
    }

    @Test
    void listar_noContent() throws Exception {
        when(service.listar()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/proyectos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void guardar_ok() throws Exception {
        when(service.guardar(any(ProyectoDTO.class))).thenReturn(proyectoDTO);

        mockMvc.perform(post("/proyectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proyectoDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void guardar_notFound() throws Exception {
        when(service.guardar(any(ProyectoDTO.class)))
                .thenThrow(new RuntimeException());

        mockMvc.perform(post("/proyectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proyectoDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorCliente_ok() throws Exception {
        when(service.buscarxId(1L))
                .thenReturn(Arrays.asList(proyectoResponseDTO));

        mockMvc.perform(get("/proyectos/cliente/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorCliente_notFound() throws Exception {
        when(service.buscarxId(1L))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/proyectos/cliente/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerProyecto_ok() throws Exception {
        when(service.buscarProyectoPorIdSimple(1L))
                .thenReturn(proyectoResponseDTO);

        mockMvc.perform(get("/proyectos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerProyecto_notFound() throws Exception {
        when(service.buscarProyectoPorIdSimple(1L))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/proyectos/1"))
                .andExpect(status().isNotFound());
    }
}