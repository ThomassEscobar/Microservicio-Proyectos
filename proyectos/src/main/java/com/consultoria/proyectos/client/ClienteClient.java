package com.consultoria.proyectos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.consultoria.proyectos.dto.ClienteDTO;

@FeignClient(name = "cliente-service",url= "http://localhost:8081" )
public interface ClienteClient {
    @GetMapping("clientes/{id}") 
    ClienteDTO obtenerClientee(@PathVariable("id") Long id);
}
