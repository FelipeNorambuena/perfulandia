package com.example.API_Inventario.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.API_Inventario.models.Inventario;
import com.example.API_Inventario.service.InventarioService;



import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping // Consultar todos los inventarios
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")    // Consultar inventario por ID
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.findById(id);
        if (inventario.isPresent()) {
            return ResponseEntity.ok(inventario.get());
        } else {
            return ResponseEntity.status(404).body("Inventario no encontrado");
        }
    }
  
    @PostMapping  // Crear nuevo inventario
    public ResponseEntity<Inventario> CrearInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.CrearInventario(inventario);
        return ResponseEntity.status(201).body(nuevoInventario);
    }

   
    @PutMapping("{id}")     // Ajustar inventario por ID
        public ResponseEntity<Inventario> ActualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
            Inventario inventarioActualizado = inventarioService.ActualizarInventario(id, inventario);
            return ResponseEntity.ok(inventarioActualizado);
        }

    

    @DeleteMapping("/{id}")         // Eliminar inventario por ID
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (inventarioService.findById(id).isPresent()) {
            inventarioService.deleteById(id);
            return ResponseEntity.ok("Inventario eliminado exitosamente");
        }
        return ResponseEntity.status(404).body("Inventario no encontrado");
    }

    
}

