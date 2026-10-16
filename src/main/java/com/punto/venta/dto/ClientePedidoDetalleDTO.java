package com.punto.venta.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientePedidoDetalleDTO {
    private String nombreCliente;
    private String apellidoCliente;
    private Date fechaPedido;
    private Integer cantidad;
    private String nombreProducto;
    private BigDecimal precio;
    private BigDecimal total;
}
