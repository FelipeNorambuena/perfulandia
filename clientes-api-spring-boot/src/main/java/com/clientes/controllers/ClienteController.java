package com.clientes.controllers;

import com.clientes.dto.ClienteDTO;
import com.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<ClienteDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(dto -> {
                    dto.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(id)).withSelfRel());
                    dto.add(linkTo(methodOn(ClienteController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    dto.add(linkTo(methodOn(ClienteController.class).eliminarHATEOAS(id)).withRel("eliminar"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/clientes/" + dto.getIdCliente()).withSelfRel());
                    dto.add(Link.of("http://localhost:8888/api/proxy/clientes/" + dto.getIdCliente()).withRel("Modificar HATEOAS").withType("PUT"));
                    dto.add(Link.of("http://localhost:8888/api/proxy/clientes/" + dto.getIdCliente()).withRel("Eliminar HATEOAS").withType("DELETE"));
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosHATEOAS() {
        List<ClienteDTO> lista = service.listar();
        for (ClienteDTO dto : lista) {
            dto.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(dto.getIdCliente())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/clientes").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/clientes/" + dto.getIdCliente()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<ClienteDTO> crearHATEOAS(@RequestBody ClienteDTO dto) {
        ClienteDTO creado = service.guardar(dto);
        creado.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(creado.getIdCliente())).withSelfRel());
        creado.add(linkTo(methodOn(ClienteController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(ClienteController.class).actualizarHATEOAS(creado.getIdCliente(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(ClienteController.class).eliminarHATEOAS(creado.getIdCliente())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + creado.getIdCliente()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + creado.getIdCliente()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + creado.getIdCliente()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<ClienteDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto)
                .map(actualizado -> {
                    actualizado.add(linkTo(methodOn(ClienteController.class).obtenerHATEOAS(id)).withSelfRel());
                    actualizado.add(linkTo(methodOn(ClienteController.class).obtenerTodosHATEOAS()).withRel("todos"));
                    actualizado.add(linkTo(methodOn(ClienteController.class).eliminarHATEOAS(id)).withRel("eliminar"));
                    actualizado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + actualizado.getIdCliente()).withSelfRel());
                    actualizado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + actualizado.getIdCliente()).withRel("Modificar HATEOAS").withType("PUT"));
                    actualizado.add(Link.of("http://localhost:8888/api/proxy/clientes/" + actualizado.getIdCliente()).withRel("Eliminar HATEOAS").withType("DELETE"));
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<ClienteDTO> eliminarHATEOAS(@PathVariable Integer id) {
        if (service.eliminar(id)) {
            ClienteDTO eliminado = new ClienteDTO();
            eliminado.setIdCliente(id);
            eliminado.add(linkTo(methodOn(ClienteController.class).obtenerTodosHATEOAS()).withRel("todos"));
            eliminado.add(linkTo(methodOn(ClienteController.class).crearHATEOAS(null)).withRel("crear"));
            eliminado.add(Link.of("http://localhost:8888/api/proxy/clientes").withRel("Get todos HATEOAS"));
            eliminado.add(Link.of("http://localhost:8888/api/proxy/clientes").withRel("Crear HATEOAS").withType("POST"));
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}