package com.punto.venta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.punto.venta.dto.ClienteDTO;
import com.punto.venta.dto.PedidoDTO;
import com.punto.venta.entity.Cliente;
import com.punto.venta.entity.Pedido;
import com.punto.venta.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRespository) {
        this.pedidoRepository = pedidoRespository;
    }

    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PedidoDTO> mostrarActivos() {
        return pedidoRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PedidoDTO> mostrarPedidoCliente() {
        return pedidoRepository.mostrarPedidoCliente();
    }

    public PedidoDTO crear(PedidoDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(dto.getClienteDTO().getIdCliente());

        boolean tienePedidoAbierto = pedidoRepository.existsByIdClienteAndEstadoPedidoFalse(cliente);
        if (tienePedidoAbierto) {
            throw new RuntimeException("El cliente ya tiene un pedido abierto");
        }

        return convertToDTO(pedidoRepository.save(convertToEntity(dto)));
    }

    public PedidoDTO anular(Integer idPedido, PedidoDTO dto) {
        Pedido pedidoExistente = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        pedidoExistente.setEstado(false);
        return convertToDTO(pedidoRepository.save(pedidoExistente));
    }

    private PedidoDTO convertToDTO(Pedido c) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(c.getIdPedido());
        dto.setEstado(c.getEstado());
        dto.setEstadoPedido(c.getEstadoPedido());
        Cliente cli = c.getIdCliente();
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cli.getIdCliente());
        clienteDTO.setNombre(cli.getNombre());
        clienteDTO.setApellido(cli.getApellido());
        clienteDTO.setEmail(cli.getEmail());
        clienteDTO.setTelefono(cli.getTelefono());
        dto.setClienteDTO(clienteDTO);
        dto.setFechaPedido(c.getFechaPedido());
        dto.setEstadoPedido(c.getEstadoPedido());
        dto.setTotal(c.getTotal());
        return dto;
    }

    private Pedido convertToEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(dto.getIdPedido());
        pedido.setEstado(dto.getEstado());
        Cliente cliente = new Cliente();
        cliente.setIdCliente(dto.getClienteDTO().getIdCliente());
        pedido.setIdCliente(cliente);
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setTotal(dto.getTotal());
        pedido.setEstadoPedido(dto.getEstadoPedido());
        return pedido;
    }
}
