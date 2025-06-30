package com.example.API_Venta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Venta.dto.DetalleVentaDTO;
import com.example.API_Venta.services.DetalleVentaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/detalleventa")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVentaDTO> listarTodos() {
        return detalleVentaService.listarTodos();
    }

    @PostMapping
    public DetalleVentaDTO crearDetalleVenta(@RequestBody DetalleVentaDTO detalleVentaDTO) {
        return detalleVentaService.crear(detalleVentaDTO);
    }

    @GetMapping("/{id}")
    public DetalleVentaDTO obtenerPorId(@PathVariable Integer id) {
        return detalleVentaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public DetalleVentaDTO actualizarDetalleVenta(@PathVariable Integer id, @RequestBody DetalleVentaDTO detalleVentaDTO) {
        return detalleVentaService.actualizar(id, detalleVentaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalleVenta(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
    }

    // MÉTODOS HATEOAS

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<DetalleVentaDTO> obtenerHATEOAS(@PathVariable Integer id) {
        DetalleVentaDTO dto = detalleVentaService.obtenerPorId(id);
        dto.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(DetalleVentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(DetalleVentaController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + dto.getId_detalle()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + dto.getId_detalle()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + dto.getId_detalle()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<DetalleVentaDTO>> obtenerTodosHATEOAS() {
        List<DetalleVentaDTO> lista = detalleVentaService.listarTodos();
        for (DetalleVentaDTO dto : lista) {
            dto.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(dto.getId_detalle())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/detalleventa").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + dto.getId_detalle()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<DetalleVentaDTO> crearHATEOAS(@RequestBody DetalleVentaDTO dto) {
        DetalleVentaDTO creado = detalleVentaService.crear(dto);
        creado.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(creado.getId_detalle())).withSelfRel());
        creado.add(linkTo(methodOn(DetalleVentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(DetalleVentaController.class).actualizarHATEOAS(creado.getId_detalle(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(DetalleVentaController.class).eliminarHATEOAS(creado.getId_detalle())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + creado.getId_detalle()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + creado.getId_detalle()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + creado.getId_detalle()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<DetalleVentaDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody DetalleVentaDTO dto) {
        DetalleVentaDTO actualizado = detalleVentaService.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(DetalleVentaController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(DetalleVentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(DetalleVentaController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + actualizado.getId_detalle()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + actualizado.getId_detalle()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/detalleventa/" + actualizado.getId_detalle()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<DetalleVentaDTO> eliminarHATEOAS(@PathVariable Integer id) {
        detalleVentaService.eliminar(id);
        DetalleVentaDTO eliminado = new DetalleVentaDTO();
        eliminado.setId_detalle(id);
        eliminado.add(linkTo(methodOn(DetalleVentaController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(DetalleVentaController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/detalleventa").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/detalleventa").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
