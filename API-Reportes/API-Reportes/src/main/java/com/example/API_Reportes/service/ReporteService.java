package com.example.API_Reportes.service;

import com.example.API_Reportes.models.Reporte;
import com.example.API_Reportes.repository.ReporteRepository;
import com.example.API_Reportes.dto.ReporteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    private ReporteDTO toDTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId_reporte(reporte.getId_reporte());
        dto.setTipo_reporte(reporte.getTipo_reporte());
        dto.setFecha_generacion(reporte.getFecha_generacion());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setJson_datos(reporte.getJson_datos());
        return dto;
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId_reporte(dto.getId_reporte());
        reporte.setTipo_reporte(dto.getTipo_reporte());
        reporte.setFecha_generacion(dto.getFecha_generacion());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setJson_datos(dto.getJson_datos());
        return reporte;
    }

    public ReporteDTO crear(ReporteDTO dto) {
        Reporte reporte = toEntity(dto);
        return toDTO(reporteRepository.save(reporte));
    }

    public List<ReporteDTO> listar() {
        return reporteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO obtenerPorId(Integer id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        return toDTO(reporte);
    }

    public ReporteDTO actualizar(Integer id, ReporteDTO dto) {
        Reporte existente = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        existente.setTipo_reporte(dto.getTipo_reporte());
        existente.setFecha_generacion(dto.getFecha_generacion());
        existente.setDescripcion(dto.getDescripcion());
        existente.setJson_datos(dto.getJson_datos());

        return toDTO(reporteRepository.save(existente));
    }

    public void eliminar(Integer id) {
        reporteRepository.deleteById(id);
    }
}
