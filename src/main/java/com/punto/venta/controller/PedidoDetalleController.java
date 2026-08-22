package com.punto.venta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punto.venta.dto.MessageResponse;
import com.punto.venta.dto.PedidoDetalleDTO;
import com.punto.venta.repository.PedidoDetalleRepository;
import com.punto.venta.service.PedidoDetalleService;

@RestController
@RequestMapping("/pedido-detalles")
@CrossOrigin(origins = "*")
public class PedidoDetalleController {
    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final PedidoDetalleService pedidoDetalleService;

    public PedidoDetalleController(PedidoDetalleService pedidoDetalleService,
            PedidoDetalleRepository pedidoDetalleRepository) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.pedidoDetalleService = pedidoDetalleService;
    }

    @GetMapping
    public List<PedidoDetalleDTO> listarTodos() {
        return pedidoDetalleService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearDetalle(@RequestBody PedidoDetalleDTO pedidoDetalleDTO) {
        try {
            pedidoDetalleService.crear(pedidoDetalleDTO);
            return ResponseEntity.ok(new MessageResponse("Detalle de pedido creado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al crear el detalle de pedido " + e.getMessage()));
        }
    }
}
