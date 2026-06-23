package com.consultoria.proyectos.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.consultoria.proyectos.model.Proyectos;
import com.consultoria.proyectos.repository.ProyectoRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private ProyectoRepository repository;

    @PostConstruct
    public void cargarDatos() {

        if(repository.count() > 0){
            return;
        }

        Proyectos p1 = new Proyectos();
        p1.setClienteId(1L);
        p1.setNombre("Sistema ERP");
        p1.setPresupuesto(50000);
        p1.setFechaInicio(new Date());

        Proyectos p2 = new Proyectos();
        p2.setClienteId(2L);
        p2.setNombre("Aplicacion Movil");
        p2.setPresupuesto(30000);
        p2.setFechaInicio(new Date());

        Proyectos p3 = new Proyectos();
        p3.setClienteId(3L);
        p3.setNombre("Plataforma Web");
        p3.setPresupuesto(45000);
        p3.setFechaInicio(new Date());

        Proyectos p4 = new Proyectos();
        p4.setClienteId(4L);
        p4.setNombre("Sistema Inventario");
        p4.setPresupuesto(25000);
        p4.setFechaInicio(new Date());

        Proyectos p5 = new Proyectos();
        p5.setClienteId(5L);
        p5.setNombre("Portal Clientes");
        p5.setPresupuesto(60000);
        p5.setFechaInicio(new Date());

        repository.saveAll(List.of(p1, p2, p3, p4, p5));

        System.out.println("Datos de proyectos cargados correctamente");
    }
}
