package com.gestion.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.dto.CrearUsuarioRequest;
import com.gestion.dto.UsuarioDTO;
import com.gestion.models.Usuario;
import com.gestion.services.UsuarioService;

import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            UsuarioDTO usuario = service.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of("mensaje", ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody CrearUsuarioRequest request) {
        Usuario creado = service.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = service.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // MÉTODOS HATEOAS

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<UsuarioDTO> obtenerHATEOAS(@PathVariable Integer id) {
        try {
            UsuarioDTO dto = service.buscarUsuarioPorId(id);
            dto.add(linkTo(methodOn(UsuarioController.class).obtenerHATEOAS(id)).withSelfRel());
            dto.add(linkTo(methodOn(UsuarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
            dto.add(linkTo(methodOn(UsuarioController.class).eliminarHATEOAS(id)).withRel("eliminar"));
            dto.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + dto.getIdUsuario()).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + dto.getIdUsuario()).withRel("Modificar HATEOAS").withType("PUT"));
            dto.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + dto.getIdUsuario()).withRel("Eliminar HATEOAS").withType("DELETE"));
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosHATEOAS() {
        List<UsuarioDTO> lista = service.listarUsuarios();
        for (UsuarioDTO dto : lista) {
            dto.add(linkTo(methodOn(UsuarioController.class).obtenerHATEOAS(dto.getIdUsuario())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/usuarios").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + dto.getIdUsuario()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<UsuarioDTO> crearHATEOAS(@RequestBody CrearUsuarioRequest request) {
        Usuario usuario = service.crearUsuario(request);
        UsuarioDTO creado = service.buscarUsuarioPorId(usuario.getIdUsuario());
        
        // Agregar enlaces HATEOAS
        creado.add(linkTo(methodOn(UsuarioController.class).obtenerHATEOAS(creado.getIdUsuario())).withSelfRel());
        creado.add(linkTo(methodOn(UsuarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(UsuarioController.class).actualizarHATEOAS(creado.getIdUsuario(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(UsuarioController.class).eliminarHATEOAS(creado.getIdUsuario())).withRel("eliminar"));
        
        // Enlaces proxy personalizados
        creado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + creado.getIdUsuario()).withRel("self-proxy"));
        creado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + creado.getIdUsuario()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + creado.getIdUsuario()).withRel("Eliminar HATEOAS").withType("DELETE"));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<UsuarioDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {
        UsuarioDTO actualizado = service.actualizarUsuario(id, dto);
        actualizado.add(linkTo(methodOn(UsuarioController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(UsuarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(UsuarioController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + actualizado.getIdUsuario()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + actualizado.getIdUsuario()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/usuarios/" + actualizado.getIdUsuario()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<UsuarioDTO> eliminarHATEOAS(@PathVariable Integer id) {
        service.eliminarUsuario(id);
        UsuarioDTO eliminado = new UsuarioDTO();
        eliminado.setIdUsuario(id);
        eliminado.add(linkTo(methodOn(UsuarioController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(UsuarioController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/usuarios").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/usuarios").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
