package com.punto.venta.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientePedidoDTO {
    private String nombreCliente;
    private String apellidoCliente;
    private Date fechaPedido;

}
