package APIsoporte.ApiSoporteTicket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import APIsoporte.ApiSoporteTicket.models.SoporteTicket;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteTicketRepository extends JpaRepository<SoporteTicket, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario




}