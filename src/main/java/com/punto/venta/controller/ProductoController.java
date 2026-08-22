package com.punto.venta.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punto.venta.dto.MessageResponse;
import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.ProductoRepository;
import com.punto.venta.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    private final ProductoRepository productoRepository;
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> listarTodos() {
        return productoService.listarProductos();
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            productoService.crear(productoDTO);
            return ResponseEntity.ok(new MessageResponse("Producto Creado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error el producto ya existe"));
        }
    }

}
