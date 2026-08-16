package com.punto.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.punto.venta.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNombreIgnoreCaseAndApellidoIgnoreCase(String nombre, String apellido);
}
