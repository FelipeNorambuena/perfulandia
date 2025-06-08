package com.example.API_Venta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Venta.dto.DetalleVentaDTO;
import com.example.API_Venta.services.DetalleVentaService;

@RestController
@RequestMapping("/api/detalleventa")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVentaDTO> listarTodos() {
        return detalleVentaService.listarTodos();
    }

    @PostMapping
    public DetalleVentaDTO crearDetalleVenta(@RequestBody DetalleVentaDTO detalleVentaDTO) {
        return detalleVentaService.crear(detalleVentaDTO);
    }

    @GetMapping("/{id}")
    public DetalleVentaDTO obtenerPorId(@PathVariable Integer id) {
        return detalleVentaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public DetalleVentaDTO actualizarDetalleVenta(@PathVariable Integer id, @RequestBody DetalleVentaDTO detalleVentaDTO) {
        return detalleVentaService.actualizar(id, detalleVentaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalleVenta(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
    }
}
