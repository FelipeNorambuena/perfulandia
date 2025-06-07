package com.clientes.controllers;

import com.clientes.dto.HistorialCompraDTO;
import com.clientes.services.HistorialCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historialcompras")
public class HistorialCompraController {

    @Autowired
    private HistorialCompraService service;

    @GetMapping
    public List<HistorialCompraDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/cliente/{idCliente}")     // Endpoint para obtener historial de compras por cliente
    public List<HistorialCompraDTO> getByCliente(@PathVariable Integer idCliente) {
        return service.getByCliente(idCliente);
    }

    @GetMapping("/{id}")            // Endpoint para obtener historial de compra por ID
    public Optional<HistorialCompraDTO> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping        // Endpoint para crear un nuevo historial de compra
    public HistorialCompraDTO create(@RequestBody HistorialCompraDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")        // Endpoint para actualizar un historial de compra existente
    public HistorialCompraDTO update(@PathVariable Integer id, @RequestBody HistorialCompraDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")     //  Endpoint para eliminar un historial de compra por ID
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
