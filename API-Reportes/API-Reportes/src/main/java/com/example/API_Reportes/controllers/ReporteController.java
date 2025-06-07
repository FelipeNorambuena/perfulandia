package com.example.API_Reportes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.API_Reportes.models.Reporte;
import com.example.API_Reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/api/reportes")



public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> getAllReportes() {
        return reporteService.getAllReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable int id) {
        Optional<Reporte> reporte = reporteService.getReporteById(id);
        return reporte.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reporte createReporte(@RequestBody Reporte reporte) {
        return reporteService.saveReporte(reporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable int id) {
        reporteService.deleteReporte(id);
        return ResponseEntity.noContent().build();
    }

}
