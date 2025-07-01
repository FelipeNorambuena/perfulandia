package com.productos.controllers;

import com.productos.dto.ProductoDTO;
import com.productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //METODOS HATEOAS

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<ProductoDTO> obtenerHATEOAS(@PathVariable Integer id) {
        ProductoDTO dto = service.obtenerPorId(id);
        //links urls de la misma API
        dto.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(ProductoController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(ProductoController.class).eliminar(id)).withRel("eliminar"));
        //link HATEOAS para API Gateway "A mano"
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosHATEOAS() {
        List<ProductoDTO> lista = service.listar();
        for (ProductoDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(dto.getId())).withSelfRel());
            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    //METODO HATEOAS para crear un producto
    @PostMapping("/hateoas")
    public ResponseEntity<ProductoDTO> crearHATEOAS(@RequestBody ProductoDTO dto) {
        ProductoDTO creado = service.crear(dto);
        creado.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(creado.getId())).withSelfRel());
        creado.add(linkTo(methodOn(ProductoController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(ProductoController.class).actualizarHATEOAS(creado.getId(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(ProductoController.class).eliminarHATEOAS(creado.getId())).withRel("eliminar"));
        //link HATEOAS para API Gateway
        creado.add(Link.of("http://localhost:8888/api/proxy/productos/" + creado.getId()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/productos/" + creado.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/productos/" + creado.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    //METODO HATEOAS para actualizar un producto
    @PutMapping("/hateoas/{id}")
    public ResponseEntity<ProductoDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        ProductoDTO actualizado = service.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(ProductoController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(ProductoController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        //link HATEOAS para API Gateway
        actualizado.add(Link.of("http://localhost:8888/api/proxy/productos/" + actualizado.getId()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/productos/" + actualizado.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/productos/" + actualizado.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    //METODO HATEOAS para eliminar un producto
    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<ProductoDTO> eliminarHATEOAS(@PathVariable Integer id) {
        service.eliminar(id);
        ProductoDTO eliminado = new ProductoDTO();
        eliminado.setId(id);
        eliminado.add(linkTo(methodOn(ProductoController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(ProductoController.class).crearHATEOAS(null)).withRel("crear"));
        //link HATEOAS para API Gateway
        eliminado.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}