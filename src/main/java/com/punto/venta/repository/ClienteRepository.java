package com.punto.venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.punto.venta.dto.ClientePedidoDTO;
import com.punto.venta.dto.ClientePedidoDetalleDTO;
import com.punto.venta.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        boolean existsByNombreIgnoreCaseAndApellidoIgnoreCase(String nombre, String apellido);

        List<Cliente> findByEstadoTrue();

        List<Cliente> findByNombreContainingIgnoreCaseAndEstadoTrue(String nombre);

        @Query("SELECT new com.punto.venta.dto.ClientePedidoDTO(c.nombre, c.apellido, p.fechaPedido) "
                        + "FROM Pedido p JOIN p.idCliente c where p.estado=true")
        List<ClientePedidoDTO> mostrarPedidosPorCliente();

        @Query("SELECT new com.punto.venta.dto.ClientePedidoDetalleDTO(c.nombre, c.apellido, pe.fechaPedido,pd.cantidad, pr.nombre, pr.precio, pd.subtotal) "
                        + "FROM PedidoDetalle pd JOIN pd.idProducto pr JOIN pd.idPedido pe JOIN  pe.idCliente c  where pe.estado=true")
        List<ClientePedidoDetalleDTO> mostrarPedidosPorClienteDetalle();
}
