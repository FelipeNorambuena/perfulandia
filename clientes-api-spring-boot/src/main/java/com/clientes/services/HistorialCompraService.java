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
        dto.setId_historial(entity.getId_historial());
        dto.setId_producto(entity.getId_producto());
        dto.setId_cliente(entity.getId_cliente());
        dto.setId_vendedor(entity.getId_vendedor());
        dto.setId_venta(entity.getId_venta());
        return dto;
    }

    private HistorialCompra toEntity(HistorialCompraDTO dto) {
        HistorialCompra entity = new HistorialCompra();
        entity.setId_historial(dto.getId_historial());
        entity.setId_producto(dto.getId_producto());
        entity.setId_cliente(dto.getId_cliente());
        entity.setId_vendedor(dto.getId_vendedor());
        entity.setId_venta(dto.getId_venta());
        
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
        return toDTO(repository.save(entity));
    }

    public HistorialCompraDTO update(Integer id, HistorialCompraDTO dto) {
        dto.setId_historial(id);
        HistorialCompra entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

