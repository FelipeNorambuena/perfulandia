package com.example.API_Envios.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.API_Envios.models.Envio;
import com.example.API_Envios.service.EnvioService;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {  // Constructor injection for EnvioService
        this.envioService = envioService;
    }


    @GetMapping
    public List<Envio> getAllEnvios() {
        return envioService.getAllEnvios();  // obtener todos los envios
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnvioById(@PathVariable Integer id) {
        return envioService.getEnvioById(id);  // obtener un envio por su ID
        
    }

    @GetMapping("/venta/{id_venta}")
    public List<Envio> getEnviosByVentaId(@PathVariable Integer id_venta) {
        return envioService.getEnviosByVentaId(id_venta);  // obtener envios por ID de venta
    }

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        return envioService.createEnvio(envio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> updateEnvio(@PathVariable Integer id, @RequestBody Envio envioDetails) {
        Envio updatedEnvio = envioService.updateEnvio(id, envioDetails);  // actualizar un envio existente
        if (updatedEnvio != null) {
            return ResponseEntity.ok(updatedEnvio);
        } else {
            return ResponseEntity.status(404).body(null);  // devolver 404 si el envio no existe
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Integer id) {
        envioService.deleteEnvio(id);  // eliminar un envio por su ID
        return ResponseEntity.noContent().build();  // devolver 204 No Content
    }
}
