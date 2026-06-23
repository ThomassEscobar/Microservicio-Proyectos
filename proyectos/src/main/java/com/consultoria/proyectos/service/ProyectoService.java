package com.consultoria.proyectos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.proyectos.client.ClienteClient;
import com.consultoria.proyectos.dto.ClienteDTO;
import com.consultoria.proyectos.dto.ProyectoDTO;
import com.consultoria.proyectos.dto.ProyectoResponseDTO;
import com.consultoria.proyectos.model.Proyectos;
import com.consultoria.proyectos.repository.ProyectoRepository;
@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository repo;

    @Autowired
    private ClienteClient client;

    public List<ProyectoResponseDTO> listar(){
        return repo.findAll()
            .stream()
            .map(pro ->{
                ClienteDTO cliente = client.obtenerClientee(pro.getClienteId());
                return new ProyectoResponseDTO(
                    pro.getId(),
                    pro.getClienteId(),
                    pro.getNombre(),
                    pro.getPresupuesto(),
                    pro.getFechaInicio(),
                    cliente
                );
            }).collect(Collectors.toList());
    }

    public ProyectoDTO guardar(ProyectoDTO dto){
        Proyectos pro = new Proyectos();
        pro.setClienteId(dto.getClienteId());
        pro.setId(dto.getId());
        pro.setNombre(dto.getNombre());
        pro.setPresupuesto(dto.getPresupuesto());
        pro.setFechaInicio(dto.getFechaInicio());
        Proyectos guardar = repo.save(pro);
        return new ProyectoDTO(
            guardar.getClienteId(),
            guardar.getId(),
            guardar.getNombre(),
            guardar.getPresupuesto(),
            guardar.getFechaInicio()
        );
    }
    public List<ProyectoResponseDTO> buscarxId(Long clienteId){
        List<Proyectos> listar = repo.findByClienteId(clienteId);
        ClienteDTO proye = client.obtenerClientee(clienteId);
        return listar.stream().map(proyecto ->
            new ProyectoResponseDTO(
                proyecto.getId(),
                proyecto.getClienteId(),
                proyecto.getNombre(),
                proyecto.getPresupuesto(),
                proyecto.getFechaInicio(),
                proye
            )
        ).toList();
        
    }
    public ProyectoResponseDTO buscarProyectoPorIdSimple(Long id) {
    Proyectos proyecto = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

    ProyectoResponseDTO dto = new ProyectoResponseDTO();
    dto.setNombre(proyecto.getNombre());
    dto.setPresupuesto(proyecto.getPresupuesto());
    dto.setFechaInicio(proyecto.getFechaInicio());
    dto.setClienteId(proyecto.getClienteId());
    
    return dto;
}


}
