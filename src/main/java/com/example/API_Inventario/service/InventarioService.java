package com.example.API_Inventario.service;

import com.example.API_Inventario.models.Inventario;

import com.example.API_Inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {   // Constructor de la clase InventarioService
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> findAll() {     // Método para obtener todos los inventarios
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Long id) {     // Método para buscar un inventario por su ID
        return inventarioRepository.findById(id);
    }


    public Inventario save(Inventario inventario) {  // Método para guardar un nuevo inventario
        return inventarioRepository.save(inventario);       // Guardar un nuevo inventario
    }

    public void deleteById(Long id) {
        inventarioRepository.deleteById(id);        // Eliminar un inventario por su ID
    }

    public Inventario ActualizarInventario(Long id, Inventario inventario) {
        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
        inventarioExistente.setStock_disponible(inventario.getStock_disponible());
        inventarioExistente.setUbicacion_bodega(inventario.getUbicacion_bodega());
        inventarioExistente.setId_producto(inventario.getId_producto());
        return inventarioRepository.save(inventarioExistente);
    }

    public Inventario CrearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);  // Método para crear un nuevo inventario
    }

}
