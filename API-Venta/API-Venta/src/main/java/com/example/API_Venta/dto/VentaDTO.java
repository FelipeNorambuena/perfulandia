package com.example.API_Venta.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VentaDTO extends RepresentationModel<VentaDTO> {

    private Integer id_venta;
    private String id_cliente;
    private String id_vendedor;
    private Date fecha_venta;
}
