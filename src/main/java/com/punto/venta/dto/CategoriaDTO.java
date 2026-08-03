/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.punto.venta.dto;

import lombok.Data;

/**
 *
 * @author madel
 */
@Data
public class CategoriaDTO {
    private Integer idCategoria;
    private Boolean estado;
    private String nombre;
    private String descripcion;
}
