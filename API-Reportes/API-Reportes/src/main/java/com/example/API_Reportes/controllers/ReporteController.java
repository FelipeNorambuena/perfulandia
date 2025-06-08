package com.example.API_Reportes.controllers;

import com.example.API_Reportes.dto.ReporteDTO;
import com.example.API_Reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<ReporteDTO> crear(@RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(reporteService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(reporteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(reporteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
