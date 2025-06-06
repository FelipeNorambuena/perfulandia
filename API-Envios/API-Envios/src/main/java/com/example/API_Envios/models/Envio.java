package com.example.API_Envios.models;


import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Table(name = "envios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_envio;
    
    private Integer id_venta;
    private String direccion_envio;
    private String estado_envio;
    private Date fecha_envio;
    private Date fecha_entrega;


}
