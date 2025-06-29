package com.example.API_Inventario.controllers;

import com.example.API_Inventario.dto.InventarioDTO;
import com.example.API_Inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn; 

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping        // Crear un nuevo inventario
    public ResponseEntity<InventarioDTO> crear(@RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.crear(dto));
    }

    @GetMapping     // Listar todos los inventarios
    public ResponseEntity<List<InventarioDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")        // Obtener un inventario por ID
    public ResponseEntity<InventarioDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(inventarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}")        // Actualizar un inventario existente
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")     // Eliminar un inventario por ID
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/ubicacion/{ubicacionBodega}")     // Buscar inventario por ubicación de bodega
    public ResponseEntity<List<InventarioDTO>> buscarPorUbicacionBodega(@PathVariable String ubicacionBodega) {
        return ResponseEntity.ok(inventarioService.buscarPorUbicacionBodega(ubicacionBodega));
    }

    
    @GetMapping("/producto/{idProducto}")       // Buscar inventario por ID de producto
    public ResponseEntity<List<InventarioDTO>> buscarPorIdProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(inventarioService.buscarPorIdProducto(idProducto));
    }


    
    @GetMapping("/hateoas/{id}")
    public InventarioDTO obtenerHATEOAS(@PathVariable Integer id) {
    InventarioDTO dto = inventarioService.obtenerPorId(id);

    // Links de la misma API
    dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(id)).withSelfRel());
    dto.add(linkTo(methodOn(InventarioController.class).listar()).withRel("todos"));
    dto.add(linkTo(methodOn(InventarioController.class).eliminar(id)).withRel("eliminar"));

    // Links HATEOAS para API Gateway (ejemplo)
    dto.add(org.springframework.hateoas.Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId_inventario()).withSelfRel());
    dto.add(org.springframework.hateoas.Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId_inventario()).withRel("Modificar HATEOAS").withType("PUT"));
    dto.add(org.springframework.hateoas.Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId_inventario()).withRel("Eliminar HATEOAS").withType("DELETE"));

    return dto;
    }
    @GetMapping("/hateoas")
    public List<InventarioDTO> obtenerTodosHATEOAS() {
    List<InventarioDTO> lista = inventarioService.listar();

    for (InventarioDTO dto : lista) {
        // Link de la misma API
        dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(dto.getId_inventario())).withSelfRel());

        // Links HATEOAS para API Gateway (ejemplo)
        dto.add(org.springframework.hateoas.Link.of("http://localhost:8888/api/proxy/inventario").withRel("Get todos HATEOAS"));
        dto.add(org.springframework.hateoas.Link.of("http://localhost:8888/api/proxy/inventario/" + dto.getId_inventario()).withRel("Crear HATEOAS").withType("POST"));
    }

    return lista;
    }

}

