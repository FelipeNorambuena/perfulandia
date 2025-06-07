package com.example.API_Venta.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Integer id_venta;
    private String id_cliente;
    private String id_vendedor;
    private Date fecha_venta;
}
