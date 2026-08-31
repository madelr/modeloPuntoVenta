package com.punto.venta.repository;

import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    boolean existsByIdClienteAndEstadoPedidoFalse(Cliente idCliente);

    boolean existsByIdPedido(Pedido idPedido);

    List<Pedido> findByEstadoTrue();

    @Query("Select new com.punto.venta.dto.PedidoDTO(p.idPedido, p.fechaPedido, p.total, c.idCliente, c.nombre, c.telefono) from Pedido p join  p.idCliente c where p.estado=true")
    List<PedidoDTO> mostrarPedidoCliente();
}
