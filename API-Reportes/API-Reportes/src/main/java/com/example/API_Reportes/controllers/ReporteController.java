package com.example.API_Reportes.controllers;

import com.example.API_Reportes.dto.ReporteDTO;
import com.example.API_Reportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<ReporteDTO> crear(@RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(reporteService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(reporteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(reporteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // =================== MÉTODOS HATEOAS ===================
    
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<ReporteDTO> obtenerHATEOAS(@PathVariable Integer id) {
        ReporteDTO dto = reporteService.obtenerPorId(id);
        dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(ReporteController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/reportes/" + dto.getId_reporte()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/reportes/" + dto.getId_reporte()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/reportes/" + dto.getId_reporte()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<ReporteDTO>> obtenerTodosHATEOAS() {
        List<ReporteDTO> lista = reporteService.listar();
        for (ReporteDTO dto : lista) {
            dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(dto.getId_reporte())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/reportes").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/reportes/" + dto.getId_reporte()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<ReporteDTO> crearHATEOAS(@RequestBody ReporteDTO dto) {
        ReporteDTO creado = reporteService.crear(dto);
        creado.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(creado.getId_reporte())).withSelfRel());
        creado.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(ReporteController.class).actualizarHATEOAS(creado.getId_reporte(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(ReporteController.class).eliminarHATEOAS(creado.getId_reporte())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + creado.getId_reporte()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + creado.getId_reporte()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + creado.getId_reporte()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<ReporteDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        ReporteDTO actualizado = reporteService.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(ReporteController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + actualizado.getId_reporte()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + actualizado.getId_reporte()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/reportes/" + actualizado.getId_reporte()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<ReporteDTO> eliminarHATEOAS(@PathVariable Integer id) {
        reporteService.eliminar(id);
        ReporteDTO eliminado = new ReporteDTO();
        eliminado.setId_reporte(id);
        eliminado.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(ReporteController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/reportes").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/reportes").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
