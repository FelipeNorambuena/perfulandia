package com.example.API_Reportes.service;

import org.springframework.stereotype.Service;
import com.example.API_Reportes.models.Reporte;

import java.util.List;
import java.util.Optional;
import com.example.API_Reportes.repository.ReporteRepository;
@Service

public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

    public Optional<Reporte> getReporteById(int id) {
        return reporteRepository.findById(id);
    }

    public Reporte saveReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public void deleteReporte(int id) {
        reporteRepository.deleteById(id);
    }

}
