package com.punto.venta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.dto.MessageResponse;
import com.punto.venta.service.CategoriaService;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/mostrarActivos")
    public List<CategoriaDTO> mostrarActivos() {
        return categoriaService.mostrarActivos();
    }

    @GetMapping("/mostrarActivosFiltro")
    public List<CategoriaDTO> mostrarActivosFiltro(@RequestParam String nombre) {
        return categoriaService.mostrarActivosFiltro(nombre);
    }

    @GetMapping("/mostrarActivosFiltroTop")
    public List<CategoriaDTO> mostrarActivosFiltroTop2(@RequestParam String nombre) {
        return categoriaService.mostrarActivosFiltroTop2(nombre);
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<MessageResponse> actualizarCategoria(@PathVariable Integer idCategoria,
            @RequestBody CategoriaDTO categoriaDTO) {
        try {
            categoriaService.modificarCategoria(idCategoria, categoriaDTO);
            return ResponseEntity.ok(new MessageResponse("Categoría actualizada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al actualizar la categoría"));
        }
    }

    @PutMapping("/anular/{idCategoria}")
    public ResponseEntity<MessageResponse> anularCategoria(@PathVariable Integer idCategoria) {
        try {
            categoriaService.anularCategoria(idCategoria);
            return ResponseEntity.ok(new MessageResponse("Categoría anulada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular la categoría"));
        }
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

}
