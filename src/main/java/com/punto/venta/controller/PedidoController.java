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
import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.repository.ClienteRepository;
import com.punto.venta.repository.PedidoRepository;
import com.punto.venta.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDTO> listarTodos() {
        return pedidoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            pedidoService.crear(pedidoDTO);
            return ResponseEntity.ok(new MessageResponse("Pedido creado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error: El pedido ya existe"));
        }
    }

}
