package com.punto.venta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.punto.venta.dto.ProductoDTO;
import com.punto.venta.entity.Producto;
import com.punto.venta.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO crear(ProductoDTO dto) {
        boolean duplicado = productoRepository.existsByNombreIgnoreCase(dto.getNombre());
        if (duplicado) {
            throw new RuntimeException("El producto ya existe");
        }
        return convertToDTO(productoRepository.save(convertToEntity(dto)));
    }

    public ProductoDTO actualizar(Integer idProducto, ProductoDTO dto) {
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (dto.getNombre() != null) {
            productoExistente.setNombre(dto.getNombre());
        }
        if (dto.getDescripcion() != null) {
            productoExistente.setDescripcion(dto.getDescripcion());
        }
        if (dto.getPrecio() != null) {
            productoExistente.setPrecio(dto.getPrecio());
        }
        if (dto.getStock() != null) {
            productoExistente.setStock(dto.getStock());
        }
        return convertToDTO(productoRepository.save(productoExistente));
    }

    public void eliminar(Integer idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(idProducto);
    }

    private ProductoDTO convertToDTO(Producto c) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(c.getIdProducto());
        dto.setNombre(c.getNombre());
        dto.setDescripcion(c.getDescripcion());
        dto.setPrecio(c.getPrecio());
        dto.setStock(c.getStock());
        return dto;
    }

    private Producto convertToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return producto;
    }
}
