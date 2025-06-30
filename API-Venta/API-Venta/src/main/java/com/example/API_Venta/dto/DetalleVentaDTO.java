package com.example.API_Venta.dto;



import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DetalleVentaDTO extends RepresentationModel<DetalleVentaDTO>{
    private Integer id_detalle;

    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private Double precio_unitario;
}
