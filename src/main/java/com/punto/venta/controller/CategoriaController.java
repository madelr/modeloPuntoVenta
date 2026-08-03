package com.punto.venta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.punto.venta.dto.CategoriaDTO;
import com.punto.venta.service.CategoriaService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.save(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCantegoria(id);
    }

    @PutMapping("anular/{id}")
    public CategoriaDTO anularCategoria(@PathVariable Integer id) {
        return categoriaService.anularCategoria(id);
    }

    @PutMapping("modificar/{id}")
    public CategoriaDTO modificarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.modificarCategoria(id, categoriaDTO);
    }
}
