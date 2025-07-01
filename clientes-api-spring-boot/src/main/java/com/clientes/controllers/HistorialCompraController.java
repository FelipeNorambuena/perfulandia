package com.clientes.controllers;

import com.clientes.dto.HistorialCompraDTO;
import com.clientes.services.HistorialCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historialcompras")
public class HistorialCompraController {

    @Autowired
    private HistorialCompraService service;

    @GetMapping
    public List<HistorialCompraDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<HistorialCompraDTO> getByCliente(@PathVariable Integer idCliente) {
        return service.getByCliente(idCliente);
    }

    @GetMapping("/{id}")
    public Optional<HistorialCompraDTO> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public HistorialCompraDTO create(@RequestBody HistorialCompraDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public HistorialCompraDTO update(@PathVariable Integer id, @RequestBody HistorialCompraDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ========== MÉTODOS HATEOAS ==========

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<HistorialCompraDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.getById(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerPorClienteHATEOAS(dto.getIdCliente())).withRel("cliente"));
                    dto.add(linkTo(methodOn(HistorialCompraController.class).eliminarHATEOAS(id)).withRel("eliminar"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + dto.getIdHistorial()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + dto.getIdHistorial()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + dto.getIdHistorial()).withRel("Eliminar HATEOAS").withType("DELETE"));
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<HistorialCompraDTO>> obtenerTodosHATEOAS() {
        List<HistorialCompraDTO> lista = service.getAll();
        for (HistorialCompraDTO dto : lista) {
            dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerHATEOAS(dto.getIdHistorial())).withSelfRel());
            dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerPorClienteHATEOAS(dto.getIdCliente())).withRel("cliente"));
            dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras").withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/hateoas/cliente/{idCliente}")
    public ResponseEntity<List<HistorialCompraDTO>> obtenerPorClienteHATEOAS(@PathVariable Integer idCliente) {
        List<HistorialCompraDTO> lista = service.getByCliente(idCliente);
        for (HistorialCompraDTO dto : lista) {
            dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerHATEOAS(dto.getIdHistorial())).withSelfRel());
            dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerTodosHATEOAS()).withRel("todos"));
            dto.add(linkTo(methodOn(HistorialCompraController.class).obtenerPorClienteHATEOAS(idCliente)).withRel("cliente"));
            dto.add(Link.of("http://localhost:8888/api/proxy/historialcompras/cliente/" + idCliente).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/clientes/" + idCliente).withRel("Cliente HATEOAS"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<HistorialCompraDTO> crearHATEOAS(@RequestBody HistorialCompraDTO dto) {
        HistorialCompraDTO creado = service.create(dto);
        creado.add(linkTo(methodOn(HistorialCompraController.class).obtenerHATEOAS(creado.getIdHistorial())).withSelfRel());
        creado.add(linkTo(methodOn(HistorialCompraController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(HistorialCompraController.class).obtenerPorClienteHATEOAS(creado.getIdCliente())).withRel("cliente"));
        creado.add(linkTo(methodOn(HistorialCompraController.class).actualizarHATEOAS(creado.getIdHistorial(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(HistorialCompraController.class).eliminarHATEOAS(creado.getIdHistorial())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + creado.getIdHistorial()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + creado.getIdHistorial()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + creado.getIdHistorial()).withRel("Eliminar HATEOAS").withType("DELETE"));
        creado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + creado.getIdCliente()).withRel("Cliente HATEOAS"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<HistorialCompraDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody HistorialCompraDTO dto) {
        HistorialCompraDTO actualizado = service.update(id, dto);
        if (actualizado != null) {
            actualizado.add(linkTo(methodOn(HistorialCompraController.class).obtenerHATEOAS(id)).withSelfRel());
            actualizado.add(linkTo(methodOn(HistorialCompraController.class).obtenerTodosHATEOAS()).withRel("todos"));
            actualizado.add(linkTo(methodOn(HistorialCompraController.class).obtenerPorClienteHATEOAS(actualizado.getIdCliente())).withRel("cliente"));
            actualizado.add(linkTo(methodOn(HistorialCompraController.class).eliminarHATEOAS(id)).withRel("eliminar"));
            actualizado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + actualizado.getIdHistorial()).withSelfRel());
            actualizado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + actualizado.getIdHistorial()).withRel("Modificar HATEOAS").withType("PUT"));
            actualizado.add(Link.of("http://localhost:8888/api/proxy/historialcompras/" + actualizado.getIdHistorial()).withRel("Eliminar HATEOAS").withType("DELETE"));
            actualizado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + actualizado.getIdCliente()).withRel("Cliente HATEOAS"));
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<HistorialCompraDTO> eliminarHATEOAS(@PathVariable Integer id) {
        Optional<HistorialCompraDTO> historial = service.getById(id);
        if (historial.isPresent()) {
            service.delete(id);
            HistorialCompraDTO eliminado = new HistorialCompraDTO();
            eliminado.setIdHistorial(id);
            eliminado.add(linkTo(methodOn(HistorialCompraController.class).obtenerTodosHATEOAS()).withRel("todos"));
            eliminado.add(linkTo(methodOn(HistorialCompraController.class).crearHATEOAS(null)).withRel("crear"));
            eliminado.add(Link.of("http://localhost:8888/api/proxy/historialcompras").withRel("Get todos HATEOAS"));
            eliminado.add(Link.of("http://localhost:8888/api/proxy/historialcompras").withRel("Crear HATEOAS").withType("POST"));
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
