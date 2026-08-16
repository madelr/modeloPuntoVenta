package com.punto.venta.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClienteDTO2 {

    private Integer idCliente;
    private Boolean estado;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaRegistro;

}
