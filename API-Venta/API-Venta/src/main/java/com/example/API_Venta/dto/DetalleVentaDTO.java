package com.example.API_Venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Integer id_detalle;

    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private Double precio_unitario;
}
