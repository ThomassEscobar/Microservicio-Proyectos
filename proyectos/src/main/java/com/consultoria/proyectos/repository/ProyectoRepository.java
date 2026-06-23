package com.consultoria.proyectos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consultoria.proyectos.model.Proyectos;
@Repository
public interface ProyectoRepository extends  JpaRepository<Proyectos, Long>{

    List<Proyectos> findByClienteId(Long clienteId);

}
