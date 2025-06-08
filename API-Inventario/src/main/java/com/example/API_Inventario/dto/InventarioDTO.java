package com.example.API_Inventario.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {

    private Integer id_inventario;
    private Integer id_producto;
    private Integer stock_disponible; // Cantidad de stock disponible
    private String ubicacion_bodega;
}
