package com.punto.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoCategoriaDTO {
    private Integer idProducto;
    private String nombreProducto;
    private String categoriaProducto;

}
