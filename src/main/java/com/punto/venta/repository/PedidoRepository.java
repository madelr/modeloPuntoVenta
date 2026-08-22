package com.punto.venta.repository;

import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    boolean existsByIdClienteAndEstadoPedidoFalse(Cliente idCliente);
}
