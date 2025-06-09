package com.example.API_Envios.dto;

import java.sql.Date;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO {
    private Integer id_envio;
    
    private Integer id_venta;
    private String direccion_envio;
    private String estado_envio;
    private Date fecha_envio;
    private Date fecha_entrega;
}
