package com.example.API_Venta.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API_Venta.dto.VentaDTO;
import com.example.API_Venta.models.Venta;
import com.example.API_Venta.repository.VentaRepository;




@Service
public class VentaService {


         @Autowired
    private VentaRepository ventaRepository;

    private VentaDTO toDTO(Venta venta) {       //
        return new VentaDTO(
                venta.getId_venta(),
                venta.getId_cliente(),
                venta.getId_vendedor(),
                venta.getFecha_venta()
                
        );
    }

    private Venta toEntity(VentaDTO dto) {      // Convertir DTO a entidad
        Venta venta = new Venta();
        venta.setId_venta(dto.getId_venta()); // importante para actualizar
        venta.setId_vendedor(dto.getId_vendedor());
        venta.setId_cliente(dto.getId_cliente());
        venta.setFecha_venta(dto.getFecha_venta());
        
        return venta;
    }

    public VentaDTO crear(VentaDTO dto) {       // Crear una nueva venta
        Venta venta = toEntity(dto);
        return toDTO(ventaRepository.save(venta));
    }

    public List<VentaDTO> listar() {        // Listar todas las ventas
        return ventaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaDTO obtenerPorId(Integer id) {      // Obtener una venta por su ID
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("venta no encontrada"));
        return toDTO(venta);
    }

    public VentaDTO actualizar(Integer id, VentaDTO dto) {      // Actualizar una venta por su ID
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("venta no encontrada"));

        existente.setId_venta(dto.getId_venta());
        existente.setId_vendedor(dto.getId_vendedor());
        existente.setId_cliente(dto.getId_cliente());
        existente.setFecha_venta(dto.getFecha_venta());
      

        return toDTO(ventaRepository.save(existente));
    }

    public void eliminar(Integer id) {      // Eliminar una venta por su ID
        ventaRepository.deleteById(id);
    }
}
