package com.punto.venta.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.punto.venta.entity.Cliente;

import com.punto.venta.entity.Cliente;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO {
    private Integer idPedido;
    private ClienteDTO clienteDTO;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaPedido;
    private Boolean estado;
    private Boolean estadoPedido;
    private BigDecimal total;

    public PedidoDTO(Integer idPedido, Date fechaPedido, BigDecimal total,
            Integer idCliente, String nombre, String telefono) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.total = total;
        ClienteDTO c = new ClienteDTO();
        c.setIdCliente(idCliente);
        c.setNombre(nombre);
        c.setTelefono(telefono);
        this.clienteDTO = c;
    }

}
