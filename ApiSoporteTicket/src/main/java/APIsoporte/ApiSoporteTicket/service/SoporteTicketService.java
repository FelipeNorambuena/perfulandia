package APIsoporte.ApiSoporteTicket.service;

import APIsoporte.ApiSoporteTicket.models.SoporteTicket;
import APIsoporte.ApiSoporteTicket.repository.SoporteTicketRepository;
import APIsoporte.ApiSoporteTicket.dto.SoporteTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoporteTicketService {

    @Autowired
    private SoporteTicketRepository soporteTicketRepository;

    private SoporteTicketDTO toDTO(SoporteTicket ticket) {
        SoporteTicketDTO dto = new SoporteTicketDTO();
        dto.setId_ticket(ticket.getId_ticket());
        dto.setTipo_ticket(ticket.getTipo_ticket());
        dto.setDescripcion(ticket.getDescripcion());
        dto.setEstado(ticket.getEstado());
        dto.setFecha_creacion(ticket.getFecha_creacion());
        dto.setFecha_resolucion(ticket.getFecha_resolucion());
        return dto;
    }

    private SoporteTicket toEntity(SoporteTicketDTO dto) {
        SoporteTicket ticket = new SoporteTicket();
        ticket.setId_ticket(dto.getId_ticket());
        ticket.setTipo_ticket(dto.getTipo_ticket());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(dto.getEstado());
        ticket.setFecha_creacion(dto.getFecha_creacion());
        ticket.setFecha_resolucion(dto.getFecha_resolucion());
        return ticket;
    }

    public SoporteTicketDTO crear(SoporteTicketDTO dto) {
        SoporteTicket ticket = toEntity(dto);
        return toDTO(soporteTicketRepository.save(ticket));
    }

    public List<SoporteTicketDTO> listar() {
        return soporteTicketRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SoporteTicketDTO obtenerPorId(Integer id) {
        SoporteTicket ticket = soporteTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        return toDTO(ticket);
    }

    public SoporteTicketDTO actualizar(Integer id, SoporteTicketDTO dto) {
        SoporteTicket existente = soporteTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        existente.setTipo_ticket(dto.getTipo_ticket());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setFecha_creacion(dto.getFecha_creacion());
        existente.setFecha_resolucion(dto.getFecha_resolucion());

        return toDTO(soporteTicketRepository.save(existente));
    }

    public void eliminar(Integer id) {
        soporteTicketRepository.deleteById(id);
    }
}
