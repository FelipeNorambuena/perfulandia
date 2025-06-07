package com.example.API_Venta.models;


import java.util.Date;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_venta;

    private String id_cliente;
    private String id_vendedor;
    private Date fecha_venta;


}
