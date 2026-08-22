package com.punto.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.punto.venta.entity.Pedido;
import com.punto.venta.entity.PedidoDetalle;
import com.punto.venta.entity.Producto;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {

    boolean existsByIdPedidoAndIdProducto(Pedido idPedido, Producto idProducto);
}
