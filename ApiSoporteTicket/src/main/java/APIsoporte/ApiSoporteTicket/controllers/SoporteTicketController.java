package APIsoporte.ApiSoporteTicket.controllers;

import APIsoporte.ApiSoporteTicket.dto.SoporteTicketDTO;
import APIsoporte.ApiSoporteTicket.service.SoporteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte/tickets")
public class SoporteTicketController {

    @Autowired
    private SoporteTicketService soporteTicketService;

    @PostMapping
    public ResponseEntity<SoporteTicketDTO> crear(@RequestBody SoporteTicketDTO dto) {
        return ResponseEntity.ok(soporteTicketService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<SoporteTicketDTO>> listar() {
        return ResponseEntity.ok(soporteTicketService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoporteTicketDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(soporteTicketService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteTicketDTO> actualizar(@PathVariable Integer id, @RequestBody SoporteTicketDTO dto) {
        return ResponseEntity.ok(soporteTicketService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        soporteTicketService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
