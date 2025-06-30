package com.clientes.dto;

import lombok.Data;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

@Data
public class HistorialCompraDTO extends RepresentationModel<HistorialCompraDTO>{
    private Integer idHistorial;
    private Integer idProducto;
    private Integer idCliente;
    private Integer idVendedor;
    private Integer idVenta;
    private LocalDateTime fechaVenta; // Solo para mostrar, viene de la tabla ventas
}
