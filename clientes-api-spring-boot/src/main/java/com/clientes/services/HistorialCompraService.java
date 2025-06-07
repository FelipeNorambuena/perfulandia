package com.clientes.services;

import com.clientes.dto.HistorialCompraDTO;
import com.clientes.models.HistorialCompra;
import com.clientes.repository.HistorialCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistorialCompraService {

    @Autowired
    private HistorialCompraRepository repository;

    private HistorialCompraDTO toDTO(HistorialCompra entity) {
        HistorialCompraDTO dto = new HistorialCompraDTO();
        dto.setIdHistorial(entity.getIdHistorial());
        dto.setIdProducto(entity.getIdProducto());
        dto.setIdCliente(entity.getIdCliente());
        dto.setIdVendedor(entity.getIdVendedor());
        dto.setIdVenta(entity.getIdVenta());
        return dto;
    }

    private HistorialCompra toEntity(HistorialCompraDTO dto) {
        HistorialCompra entity = new HistorialCompra();
        entity.setIdHistorial(dto.getIdHistorial());
        entity.setIdProducto(dto.getIdProducto());
        entity.setIdCliente(dto.getIdCliente());
        entity.setIdVendedor(dto.getIdVendedor());
        entity.setIdVenta(dto.getIdVenta());
        return entity;
    }

    public List<HistorialCompraDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<HistorialCompraDTO> getByCliente(Integer idCliente) {
        return repository.findByIdCliente(idCliente).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<HistorialCompraDTO> getById(Integer id) {
        return repository.findById(id).map(this::toDTO);
    }

    public HistorialCompraDTO create(HistorialCompraDTO dto) {
        HistorialCompra entity = toEntity(dto);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    public HistorialCompraDTO update(Integer id, HistorialCompraDTO dto) {
        dto.setIdHistorial(id);
        HistorialCompra entity = toEntity(dto);
        entity = repository.save(entity);
        return toDTO(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

