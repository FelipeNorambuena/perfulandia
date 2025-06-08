package com.example.API_Reportes.dto;

import lombok.Data;

@Data
public class ReporteDTO {

    private Integer id_reporte;
    private String tipo_reporte;
    private String fecha_generacion;
    private String descripcion;
    private String json_datos;

}
