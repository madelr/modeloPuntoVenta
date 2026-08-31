package com.punto.venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.punto.venta.dto.ProductoCategoriaDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);

    List<Producto> findByEstadoTrue();

    List<Producto> findByNombreContainingIgnoreCaseAndEstadoTrue(String nombre);

    @Query("SELECT new com.punto.venta.dto.ProductoCategoriaDTO(p.idProducto, p.nombre, c.nombre) "
            + "FROM Producto p JOIN p.idCategoria c where p.estado=true")
    List<ProductoCategoriaDTO> mostrarProductoConCategoria();

    List<Producto> findByEstadoTrueOrderByIdProductoDesc();

    List<Producto> findTop5ByEstadoTrueOrderByIdProductoDesc();
}
