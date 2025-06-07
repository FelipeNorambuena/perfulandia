package com.clientes.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistorialCompraDTO {
    private Integer idHistorial;
    private Integer idProducto;
    private Integer idCliente;
    private Integer idVendedor;
    private Integer idVenta;
    private LocalDateTime fechaVenta; // Solo para mostrar, viene de la tabla ventas
}
