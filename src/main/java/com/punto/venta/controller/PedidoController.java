package com.punto.venta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.dto.MessageResponse;
import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.repository.ClienteRepository;
import com.punto.venta.repository.PedidoRepository;
import com.punto.venta.service.PedidoService;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/pedidoCliente")
    public List<PedidoDTO> mostrarPedidoCliente() {
        return pedidoService.mostrarPedidoCliente();
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/activos")
    public List<PedidoDTO> mostrarActivos() {
        return pedidoService.mostrarActivos();
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

    @PutMapping("anular/{idPedido}")
    public ResponseEntity<MessageResponse> anularPedido(@PathVariable Integer idPedido,
            @RequestBody PedidoDTO pedidoDTO) {
        try {
            pedidoService.anular(idPedido, pedidoDTO);
            return ResponseEntity
                    .ok(new MessageResponse("Pedido anulado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular el pedido"));
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<MessageResponse> eliminarPedido(@PathVariable Integer idPedido) {
        try {
            pedidoRepository.deleteById(idPedido);
            return ResponseEntity
                    .ok(new MessageResponse("Pedido eliminado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new MessageResponse("Error al eliminar el pedido"));
        }
    }
}
