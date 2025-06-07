package APIsoporte.ApiSoporteTicket.service;

import APIsoporte.ApiSoporteTicket.models.SoporteTicket;
import APIsoporte.ApiSoporteTicket.repository.SoporteTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SoporteTicketService {

    private final SoporteTicketRepository soporteTicketRepository;

    public SoporteTicketService(SoporteTicketRepository soporteTicketRepository) {
        this.soporteTicketRepository = soporteTicketRepository;
    }

    public List<SoporteTicket> getAllTickets() {
        return soporteTicketRepository.findAll();
    }

    public Optional<SoporteTicket> getTicketById(int id) {
        return soporteTicketRepository.findById(id);
    }

    public SoporteTicket createTicket(SoporteTicket ticket) {
        return soporteTicketRepository.save(ticket);
    }

    public SoporteTicket updateTicket(int id, SoporteTicket ticketDetails) {
        if (soporteTicketRepository.existsById(id)) {
            ticketDetails.setId_ticket(id);
            return soporteTicketRepository.save(ticketDetails);
        }
        return null;
    }

    public void deleteTicket(int id) {
        soporteTicketRepository.deleteById(id);
    }

}
