package com.punto.venta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.punto.venta.dto.MessageResponse;
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
    public ResponseEntity<MessageResponse> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            categoriaService.crearCategoria(categoriaDTO);
            return ResponseEntity.ok(new MessageResponse("Categoría creada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error: La categoría ya existe"));
        }
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
