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
    private Integer idHistorial;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_vendedor", nullable = false)
    private Integer idVendedor;

    @Column(name = "id_venta", nullable = false)
    private Integer idVenta;

    // Ya no incluyas fechaVenta aquí, solo en el DTO si lo necesitas mostrar
}
