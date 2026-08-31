package com.punto.venta.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
    private Integer idCliente;
    private Boolean estado;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaRegistro;
}
