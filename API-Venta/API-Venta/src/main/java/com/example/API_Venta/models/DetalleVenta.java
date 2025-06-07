package com.example.API_Venta.models;




import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "detalleventa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer id_detalle;

    private Integer id_venta;
    private Integer id_producto;
    private Integer cantidad;
    private double precio_unitario;
}
