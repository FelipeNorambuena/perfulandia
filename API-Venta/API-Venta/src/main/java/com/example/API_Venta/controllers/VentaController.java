package com.example.API_Venta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Venta.dto.VentaDTO;
import com.example.API_Venta.services.VentaService;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    
    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // MÉTODOS HATEOAS

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<VentaDTO> obtenerHATEOAS(@PathVariable Integer id) {
        VentaDTO dto = service.obtenerPorId(id);
        dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(VentaController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId_venta()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId_venta()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId_venta()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<VentaDTO>> obtenerTodosHATEOAS() {
        List<VentaDTO> lista = service.listar();
        for (VentaDTO dto : lista) {
            dto.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(dto.getId_venta())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/ventas").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/ventas/" + dto.getId_venta()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<VentaDTO> crearHATEOAS(@RequestBody VentaDTO dto) {
        VentaDTO creado = service.crear(dto);
        creado.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(creado.getId_venta())).withSelfRel());
        creado.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(VentaController.class).actualizarHATEOAS(creado.getId_venta(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(VentaController.class).eliminarHATEOAS(creado.getId_venta())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + creado.getId_venta()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + creado.getId_venta()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + creado.getId_venta()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<VentaDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody VentaDTO dto) {
        VentaDTO actualizado = service.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(VentaController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(VentaController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + actualizado.getId_venta()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + actualizado.getId_venta()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/ventas/" + actualizado.getId_venta()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<VentaDTO> eliminarHATEOAS(@PathVariable Integer id) {
        service.eliminar(id);
        VentaDTO eliminado = new VentaDTO();
        eliminado.setId_venta(id);
        eliminado.add(linkTo(methodOn(VentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(VentaController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/ventas").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/ventas").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }

}
