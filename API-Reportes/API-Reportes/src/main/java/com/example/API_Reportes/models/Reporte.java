package com.example.API_Reportes.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reporte;
    private String tipo_reporte;
    private String fecha_generacion;
    private String descripcion;
    private String json_datos;

}
