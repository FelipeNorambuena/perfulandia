package com.example.API_Reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.API_Reportes.models.Reporte;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

}
