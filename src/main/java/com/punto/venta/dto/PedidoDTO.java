package com.punto.venta.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.punto.venta.entity.Cliente;

import com.punto.venta.entity.Cliente;

import lombok.Data;

@Data
public class PedidoDTO {
    private Integer idPedido;
    private Integer idCliente;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaPedido;
    private Boolean estado;
    private Boolean estadoPedido;
    private BigDecimal total;
}
