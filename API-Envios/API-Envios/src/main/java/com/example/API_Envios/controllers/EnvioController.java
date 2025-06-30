package com.example.API_Envios.controllers;

import com.example.API_Envios.dto.EnvioDTO;
import com.example.API_Envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping // Crear un nuevo envío
    public ResponseEntity<EnvioDTO> crear(@RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(envioService.crear(dto));
    }

    @GetMapping // Listar todos los envíos
    public ResponseEntity<List<EnvioDTO>> listar() {
        return ResponseEntity.ok(envioService.listar());
    }

    @GetMapping("/{id}") // Obtener un envío por ID
    public ResponseEntity<EnvioDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.obtenerPorId(id));
    }

    @PutMapping("/{id}") // Actualizar un envío existente
    public ResponseEntity<EnvioDTO> actualizar(@PathVariable Integer id, @RequestBody EnvioDTO dto) {
        return ResponseEntity.ok(envioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}") // Eliminar un envío por ID
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}") // Buscar envíos por estado
    public ResponseEntity<List<EnvioDTO>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(envioService.buscarPorEstado(estado));
    }

    @GetMapping("/venta/{idVenta}") // Buscar envíos por ID de venta
    public ResponseEntity<List<EnvioDTO>> buscarPorIdVenta(@PathVariable Integer idVenta) {
        return ResponseEntity.ok(envioService.buscarPorIdVenta(idVenta));
    }

    // MÉTODOS HATEOAS

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<EnvioDTO> obtenerHATEOAS(@PathVariable Integer id) {
        EnvioDTO dto = envioService.obtenerPorId(id);
        dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(EnvioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(EnvioController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/envios/" + dto.getId_envio()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/envios/" + dto.getId_envio()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/envios/" + dto.getId_envio()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<EnvioDTO>> obtenerTodosHATEOAS() {
        List<EnvioDTO> lista = envioService.listar();
        for (EnvioDTO dto : lista) {
            dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(dto.getId_envio())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/envios").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/envios/" + dto.getId_envio()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<EnvioDTO> crearHATEOAS(@RequestBody EnvioDTO dto) {
        EnvioDTO creado = envioService.crear(dto);
        creado.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(creado.getId_envio())).withSelfRel());
        creado.add(linkTo(methodOn(EnvioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(EnvioController.class).actualizarHATEOAS(creado.getId_envio(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(EnvioController.class).eliminarHATEOAS(creado.getId_envio())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/envios/" + creado.getId_envio()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/envios/" + creado.getId_envio()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/envios/" + creado.getId_envio()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<EnvioDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody EnvioDTO dto) {
        EnvioDTO actualizado = envioService.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(EnvioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(EnvioController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/envios/" + actualizado.getId_envio()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/envios/" + actualizado.getId_envio()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/envios/" + actualizado.getId_envio()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<EnvioDTO> eliminarHATEOAS(@PathVariable Integer id) {
        envioService.eliminar(id);
        EnvioDTO eliminado = new EnvioDTO();
        eliminado.setId_envio(id);
        eliminado.add(linkTo(methodOn(EnvioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(EnvioController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/envios").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/envios").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
