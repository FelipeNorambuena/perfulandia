package com.clientes.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "historial_compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_historial;
    
    private Integer id_producto;
    private Integer id_cliente;
    private Integer id_vendedor;
    private Integer id_venta;
    
}
