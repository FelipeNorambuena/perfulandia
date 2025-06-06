package com.example.API_Vendedor.dto;

import lombok.Data;

@Data
public class VendedorDTO {

    private Integer id_vendedor;
    private Integer id_usuario;
    private String nombre_completo;
    private String rut;
    private String area_ventas;

}
