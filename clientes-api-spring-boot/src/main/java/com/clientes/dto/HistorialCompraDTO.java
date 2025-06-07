package com.clientes.dto;

import lombok.Data;


@Data
public class HistorialCompraDTO {
    private Integer id_historial;
    private Integer id_producto;
    private Integer id_cliente;
    private Integer id_vendedor;
    private Integer id_venta;

}
