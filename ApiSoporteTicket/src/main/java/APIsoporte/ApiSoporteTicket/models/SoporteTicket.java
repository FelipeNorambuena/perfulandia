package APIsoporte.ApiSoporteTicket.models;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SoporteTicket {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_ticket")
    	private Integer id_ticket;
        private String tipo_ticket;
        private String descripcion;
        private String estado;	
        private Date fecha_creacion;
        private Date fecha_resolucion;
}
