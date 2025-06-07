package APIsoporte.ApiSoporteTicket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import APIsoporte.ApiSoporteTicket.models.SoporteTicket;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/soporte")

public class SoporteTicketController {

    // Aquí se inyectará el servicio de SoporteTicketService
    // private final SoporteTicketService soporteTicketService;

    // Constructor para inyectar el servicio
    // public SoporteTicketController(SoporteTicketService soporteTicketService) {
    //     this.soporteTicketService = soporteTicketService;
    // }

    @GetMapping("/tickets")
    public ResponseEntity<List<SoporteTicket>> getAllTickets() {
        // Lógica para obtener todos los tickets
        return ResponseEntity.ok().body(List.of());
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Optional<SoporteTicket>> getTicketById(@PathVariable int id) {
        // Lógica para obtener un ticket por ID
        return ResponseEntity.ok().body(Optional.empty());
    }

    @PostMapping("/tickets")
    public ResponseEntity<SoporteTicket> createTicket(@RequestBody SoporteTicket ticket) {
        // Lógica para crear un nuevo ticket
        return ResponseEntity.ok().body(ticket);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<SoporteTicket> updateTicket(@PathVariable int id, @RequestBody SoporteTicket ticket) {
        // Lógica para actualizar un ticket existente
        return ResponseEntity.ok().body(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        // Lógica para eliminar un ticket por ID
        return ResponseEntity.noContent().build();
    }
}
