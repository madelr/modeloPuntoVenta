package com.punto.venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punto.venta.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNombreIgnoreCaseAndApellidoIgnoreCase(String nombre, String apellido);

    List<Cliente> findByEstadoTrue();

    List<Cliente> findByNombreContainingIgnoreCaseAndEstadoTrue(String nombre);
}
