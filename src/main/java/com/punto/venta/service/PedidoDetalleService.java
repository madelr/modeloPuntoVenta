package com.punto.venta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.dto.PedidoDetalleDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Pedido;
import com.punto.venta.entity.Producto;
import com.punto.venta.entity.PedidoDetalle;
import com.punto.venta.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService {
    private final PedidoDetalleRepository pedidoDetalleRepository;

    public PedidoDetalleService(PedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }

    public List<PedidoDetalleDTO> listarTodos() {
        return pedidoDetalleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PedidoDetalleDTO crear(PedidoDetalleDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(dto.getIdPedido());
        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());
        boolean duplicado = pedidoDetalleRepository.existsByIdPedidoAndIdProducto(pedido, producto);
        if (duplicado) {
            throw new RuntimeException("El detalle de pedido ya existe");
        }
        return convertToDTO(pedidoDetalleRepository.save(convertToEntity(dto)));
    }

    private PedidoDetalleDTO convertToDTO(PedidoDetalle c) {
        PedidoDetalleDTO dto = new PedidoDetalleDTO();
        dto.setIdPedidoDetalle(c.getIdPedidoDetalle());
        dto.setIdPedido(c.getIdPedido().getIdPedido());
        dto.setIdProducto(c.getIdProducto().getIdProducto());
        dto.setCantidad(c.getCantidad());
        dto.setPrecioUnitario(c.getPrecioUnitario());
        dto.setSubTotal(c.getSubtotal());
        return dto;
    }

    private PedidoDetalle convertToEntity(PedidoDetalleDTO c) {
        PedidoDetalle dto = new PedidoDetalle();
        dto.setIdPedidoDetalle(c.getIdPedidoDetalle());
        Pedido pedido = new Pedido();
        pedido.setIdPedido(c.getIdPedido());
        dto.setIdPedido(pedido);
        Producto producto = new Producto();
        producto.setIdProducto(c.getIdProducto());
        dto.setIdProducto(producto);
        dto.setCantidad(c.getCantidad());
        dto.setPrecioUnitario(c.getPrecioUnitario());
        dto.setSubtotal(c.getSubTotal());
        return dto;
    }

}
