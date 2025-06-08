package API_Vendedor.API_VENDEDOR.models;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "vendedores")
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vendedor;
    private Integer id_usuario;
    private String nombre_completo;
    private String rut;
    private String area_ventas;
    private Integer meta;
}
