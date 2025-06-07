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

    @GetMapping("/cliente/{idCliente}")
    public List<HistorialCompraDTO> getByCliente(@PathVariable Integer idCliente) {
        return service.getByCliente(idCliente);
    }

    @GetMapping("/{id}")
    public Optional<HistorialCompraDTO> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public HistorialCompraDTO create(@RequestBody HistorialCompraDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public HistorialCompraDTO update(@PathVariable Integer id, @RequestBody HistorialCompraDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
