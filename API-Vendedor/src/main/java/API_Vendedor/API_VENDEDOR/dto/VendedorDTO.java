package API_Vendedor.API_VENDEDOR.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data

public class VendedorDTO extends RepresentationModel<VendedorDTO>{

    private Integer id_vendedor;
    private Integer id_usuario;
    private String nombre_completo;
    private String rut;
    private String area_ventas;
    private Integer meta;
}
