package com.example.API_Inventario.service;

import com.example.API_Inventario.models.Inventario;
import com.example.API_Inventario.repository.InventarioRepository;
import com.example.API_Inventario.dto.InventarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    private InventarioDTO toDTO(Inventario inventario) {
        return new InventarioDTO(
                inventario.getId_inventario(),
                inventario.getId_producto(),
                inventario.getStock_disponible(),
                inventario.getUbicacion_bodega()
        );
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setId_inventario(dto.getId_inventario());
        inventario.setId_producto(dto.getId_producto());
        inventario.setStock_disponible(dto.getStock_disponible());
        inventario.setUbicacion_bodega(dto.getUbicacion_bodega());
        return inventario;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public List<InventarioDTO> listar() {
        return inventarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public InventarioDTO obtenerPorId(Integer id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        return toDTO(inventario);
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto) {
        Inventario existente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        existente.setId_producto(dto.getId_producto());
        existente.setStock_disponible(dto.getStock_disponible());
        existente.setUbicacion_bodega(dto.getUbicacion_bodega());

        return toDTO(inventarioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        inventarioRepository.deleteById(id);
    }

    // Buscar inventario por ubicación de bodega
    public List<InventarioDTO> buscarPorUbicacionBodega(String ubicacionBodega) {
        return inventarioRepository.findAll().stream()
                .filter(inv -> inv.getUbicacion_bodega().equalsIgnoreCase(ubicacionBodega))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar inventario por id de producto
    public List<InventarioDTO> buscarPorIdProducto(Integer idProducto) {
        return inventarioRepository.findAll().stream()
                .filter(inv -> inv.getId_producto().equals(idProducto))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
