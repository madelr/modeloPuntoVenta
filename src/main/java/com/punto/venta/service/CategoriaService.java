package com.punto.venta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.punto.venta.repository.CategoriaRepository;
import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.entity.Categoria;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        boolean duplicado = categoriaRepository.existsByNombreIgnoreCase(dto.getNombre());
        if (duplicado) {
            throw new RuntimeException("La categoria ya existe");
        }
        return convertToDTO(categoriaRepository.save(convertToEntity(dto)));
    }

    public void eliminarCantegoria(Integer idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new RuntimeException("La categoria no existe con id " + idCategoria);
        }
        categoriaRepository.deleteById(idCategoria);
    }

    public CategoriaDTO anularCategoria(Integer idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("La categoria no existe con id " + idCategoria));
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setEstado(false);
        categoria.setEstado(categoriaDTO.getEstado());

        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    public CategoriaDTO modificarCategoria(Integer idCategoria, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("La categoria no existe con id " + idCategoria));

        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    private CategoriaDTO convertToDTO(Categoria c) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(c.getIdCategoria());
        dto.setNombre(c.getNombre());
        dto.setDescripcion(c.getDescripcion());
        dto.setEstado(c.getEstado());
        return dto;
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setEstado(true);
        return categoria;
    }

}
