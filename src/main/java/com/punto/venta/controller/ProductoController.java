package com.punto.venta.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.dto.MessageResponse;
import com.punto.venta.dto.ProductoCategoriaDTO;
import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.ProductoRepository;
import com.punto.venta.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/activos")
    public List<ProductoDTO> mostrarActivos() {
        return productoService.mostrarActivos();
    }

    @GetMapping("/activosOrden")
    public List<ProductoDTO> mostrarActivosOrden() {
        return productoService.mostrarActivosOrden();
    }

    @GetMapping("/activosOrdenTop5")
    public List<ProductoDTO> mostrarActivosOrdenTop5() {
        return productoService.mostrarActivosOrdenTop5();
    }

    @GetMapping("/filtroNombre")
    public List<ProductoDTO> filtroNombre(String nombre) {
        return productoService.filtroNombre(nombre);
    }

    @GetMapping("/productoCategoria")
    public List<ProductoCategoriaDTO> listarConCategoria() {
        return productoService.listarProductoConCategoria();
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

    @PutMapping("/{idPedido}")
    public ResponseEntity<MessageResponse> actualizarProducto(@PathVariable Integer idProducto,
            @RequestBody ProductoDTO productoDTO) {
        try {
            productoService.actualizar(idProducto, productoDTO);
            return ResponseEntity
                    .ok(new MessageResponse("Producto actualizado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al actualizar el producto"));
        }
    }

    @PutMapping("anular/{idProducto}")
    public ResponseEntity<MessageResponse> anularProducto(@PathVariable Integer idProducto,
            @RequestBody ProductoDTO productoDTO) {
        try {
            productoService.anular(idProducto, productoDTO);
            return ResponseEntity
                    .ok(new MessageResponse("Producto anulado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular el Producto"));
        }
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<MessageResponse> eliminarProducto(@PathVariable Integer idProducto) {
        try {
            productoRepository.deleteById(idProducto);
            return ResponseEntity
                    .ok(new MessageResponse("Producto eliminado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new MessageResponse("Error al eliminar el producto"));
        }
    }
}
