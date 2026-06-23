package com.consultoria.proyectos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.consultoria.proyectos.client.ClienteClient;
import com.consultoria.proyectos.dto.ClienteDTO;
import com.consultoria.proyectos.dto.ProyectoDTO;
import com.consultoria.proyectos.dto.ProyectoResponseDTO;
import com.consultoria.proyectos.model.Proyectos;
import com.consultoria.proyectos.repository.ProyectoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {

    @Mock
    private ProyectoRepository repo;

    @Mock
    private ClienteClient client;

    @InjectMocks
    private ProyectoService service;

    private Proyectos proyecto;
    private ClienteDTO clienteMock;

    @BeforeEach
    public void setUp() {
        proyecto = new Proyectos();
        proyecto.setId(1L);
        proyecto.setClienteId(10L);
        proyecto.setNombre("Sistema de Auditoría");
        proyecto.setPresupuesto(50000); // Integer nativo para la Entidad
        proyecto.setFechaInicio(new Date()); // Date nativo para la Entidad

        clienteMock = new ClienteDTO();
        clienteMock.setId(10L);
        clienteMock.setNombreEmpresa("Tech Solutions");
        clienteMock.setNit(1001L);
        clienteMock.setSector("Tecnologia");
    }

    // 1. TEST LISTAR
    @Test
    public void testListar() {
        // Arrange
        when(repo.findAll()).thenReturn(List.of(proyecto));
        when(client.obtenerClientee(10L)).thenReturn(clienteMock);

        // Act
        List<ProyectoResponseDTO> resultado = service.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Sistema de Auditoría", resultado.get(0).getNombre());
        assertEquals(50000, resultado.get(0).getPresupuesto()); // Comparación limpia de enteros
        assertNotNull(resultado.get(0).getCliente());

        verify(repo, times(1)).findAll();
    }

    // 2. TEST GUARDAR
    @Test
    public void testGuardar() {
        // Arrange
        ProyectoDTO dtoInput = new ProyectoDTO();
        dtoInput.setId(null);
        dtoInput.setClienteId(10L);
        dtoInput.setNombre("Sistema de Auditoría");
        dtoInput.setPresupuesto(50000); // Integer
        dtoInput.setFechaInicio(new Date());

        when(repo.save(any(Proyectos.class))).thenReturn(proyecto);

        // Act
        ProyectoDTO resultado = service.guardar(dtoInput);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(10L, resultado.getClienteId());
        assertEquals("Sistema de Auditoría", resultado.getNombre());
        assertEquals(50000, resultado.getPresupuesto()); // Comparación limpia de enteros

        verify(repo, times(1)).save(any(Proyectos.class));
    }

    // 3. TEST BUSCAR POR CLIENTE ID
    @Test
    public void testBuscarxId() {
        // Arrange
        when(repo.findByClienteId(10L)).thenReturn(List.of(proyecto));
        when(client.obtenerClientee(10L)).thenReturn(clienteMock);

        // Act
        List<ProyectoResponseDTO> resultado = service.buscarxId(10L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(50000, resultado.get(0).getPresupuesto());

        verify(repo, times(1)).findByClienteId(10L);
    }

    // 4. TEST BUSCAR PROYECTO POR ID SIMPLE (Caso Exitoso)
    @Test
    public void testBuscarProyectoPorIdSimple_Exitoso() {
        // Arrange
        when(repo.findById(1L)).thenReturn(Optional.of(proyecto));

        // Act
        ProyectoResponseDTO resultado = service.buscarProyectoPorIdSimple(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Sistema de Auditoría", resultado.getNombre());
        assertEquals(50000, resultado.getPresupuesto()); // Comparación limpia de enteros
        assertEquals(10L, resultado.getClienteId());

        verify(repo, times(1)).findById(1L);
    }

    // 5. TEST BUSCAR PROYECTO POR ID SIMPLE (Caso Error)
    @Test
    public void testBuscarProyectoPorIdSimple_NoEncontrado() {
        // Arrange
        when(repo.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            service.buscarProyectoPorIdSimple(2L);
        });

        verify(repo, times(1)).findById(2L);
    }
}