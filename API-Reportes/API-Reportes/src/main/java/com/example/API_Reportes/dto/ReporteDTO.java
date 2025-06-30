package com.example.API_Reportes.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class ReporteDTO extends RepresentationModel<ReporteDTO> {

    private Integer id_reporte;
    private String tipo_reporte;
    private String fecha_generacion;
    private String descripcion;
    private String json_datos;

}
