package APIsoporte.ApiSoporteTicket.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SoporteTicketDTO {

        private int id_ticket;
        private String tipo_ticket;
        private String descripcion;
        private String estado;	
        private Date fecha_creacion;
        private Date fecha_resolucion;
}
