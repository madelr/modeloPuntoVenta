package com.punto.venta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.dto.MessageResponse;
import com.punto.venta.repository.ClienteRepository;
import com.punto.venta.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService,
            ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listarTodos() {
        return clienteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.crear(clienteDTO);
            return ResponseEntity.ok(new MessageResponse("Cliente Creado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error el cliente ya existe"));
        }
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<MessageResponse> actualizarCliente(@PathVariable Integer idCliente,
            @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.actualizar(idCliente, clienteDTO);
            return ResponseEntity.ok(new MessageResponse("Cliente actualizado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al actualizar el cliente"));
        }
    }

    @PutMapping("anular/{idCliente}")
    public ResponseEntity<MessageResponse> anularCliente(@PathVariable Integer idCliente,
            @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.anularCliente(idCliente);
            return ResponseEntity.ok(new MessageResponse("Cliente anulado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al anular el cliente"));
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<MessageResponse> eliminarCliente(@PathVariable Integer idCliente) {
        try {
            clienteService.eliminar(idCliente);
            return ResponseEntity.ok(new MessageResponse("Cliente eliminado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Error al eliminar el cliente " + e.getMessage()));
        }
    }

}
