package com.punto.venta.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoDetalleDTO {
    private Integer idPedidoDetalle;
    private Integer idPedido;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
}
