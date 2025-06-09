package com.example.API_Envios.controllers;

import com.example.API_Envios.dto.EnvioDTO;
import com.example.API_Envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
