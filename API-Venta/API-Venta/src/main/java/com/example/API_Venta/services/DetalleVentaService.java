package com.example.API_Venta.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API_Venta.dto.DetalleVentaDTO;
import com.example.API_Venta.models.DetalleVenta;
import com.example.API_Venta.repository.DetalleVentaRepository;



@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detallerepository;

    private DetalleVentaDTO toDTO(DetalleVenta detalle) {       // Convierte un DetalleVenta a DetalleVentaDTO
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setId_detalle(detalle.getId_detalle());
        dto.setId_venta(detalle.getId_venta());
        dto.setId_producto(detalle.getId_producto());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecio_unitario(detalle.getPrecio_unitario());
        
        return dto;
    }

    private DetalleVenta toEntity(DetalleVentaDTO dto) {        // Convierte un DetalleVentaDTO a DetalleVenta
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId_detalle(dto.getId_detalle());
        detalle.setId_venta(dto.getId_venta());
        detalle.setId_producto(dto.getId_producto());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecio_unitario(dto.getPrecio_unitario());
        
        return detalle;
    }

    public DetalleVentaDTO crear(DetalleVentaDTO dto) {     // Crea un nuevo DetalleVenta a partir de DetalleVentaDTO
        DetalleVenta detalle = toEntity(dto);
        return toDTO(detallerepository.save(detalle));
    }

   

    public DetalleVentaDTO actualizar(Integer id, DetalleVentaDTO dto) {        // Actualiza un DetalleVenta existente con los datos de DetalleVentaDTO
        DetalleVenta existente = detallerepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleVenta no encontrado"));

        existente.setId_venta(dto.getId_venta());
        existente.setId_producto(dto.getId_producto());
        existente.setCantidad(dto.getCantidad());
        existente.setPrecio_unitario(dto.getPrecio_unitario());

        return toDTO(detallerepository.save(existente));
    }

    public void eliminar(Integer id) {      // Elimina un DetalleVenta por su ID
        detallerepository.deleteById(id);
    }
}
