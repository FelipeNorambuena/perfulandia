package APIsoporte.ApiSoporteTicket.controllers;

import APIsoporte.ApiSoporteTicket.dto.SoporteTicketDTO;
import APIsoporte.ApiSoporteTicket.service.SoporteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
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

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<SoporteTicketDTO> obtenerHATEOAS(@PathVariable Integer id) {
        SoporteTicketDTO dto = soporteTicketService.obtenerPorId(id);
        dto.add(linkTo(methodOn(SoporteTicketController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(SoporteTicketController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(SoporteTicketController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + dto.getId_ticket()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + dto.getId_ticket()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + dto.getId_ticket()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<SoporteTicketDTO>> obtenerTodosHATEOAS() {
        List<SoporteTicketDTO> lista = soporteTicketService.listar();
        for (SoporteTicketDTO dto : lista) {
            dto.add(linkTo(methodOn(SoporteTicketController.class).obtenerHATEOAS(dto.getId_ticket())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + dto.getId_ticket()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<SoporteTicketDTO> crearHATEOAS(@RequestBody SoporteTicketDTO dto) {
        SoporteTicketDTO creado = soporteTicketService.crear(dto);
        creado.add(linkTo(methodOn(SoporteTicketController.class).obtenerHATEOAS(creado.getId_ticket())).withSelfRel());
        creado.add(linkTo(methodOn(SoporteTicketController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(SoporteTicketController.class).actualizarHATEOAS(creado.getId_ticket(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(SoporteTicketController.class).eliminarHATEOAS(creado.getId_ticket())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + creado.getId_ticket()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + creado.getId_ticket()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + creado.getId_ticket()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<SoporteTicketDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody SoporteTicketDTO dto) {
        SoporteTicketDTO actualizado = soporteTicketService.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(SoporteTicketController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(SoporteTicketController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(SoporteTicketController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + actualizado.getId_ticket()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + actualizado.getId_ticket()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets/" + actualizado.getId_ticket()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<SoporteTicketDTO> eliminarHATEOAS(@PathVariable Integer id) {
        soporteTicketService.eliminar(id);
        SoporteTicketDTO eliminado = new SoporteTicketDTO();
        eliminado.setId_ticket(id);
        eliminado.add(linkTo(methodOn(SoporteTicketController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(SoporteTicketController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/soporte/tickets").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
