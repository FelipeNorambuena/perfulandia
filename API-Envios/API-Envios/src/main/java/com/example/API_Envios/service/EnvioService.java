package com.example.API_Envios.service;

import com.example.API_Envios.dto.EnvioDTO;
import com.example.API_Envios.models.*;
import com.example.API_Envios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    private EnvioDTO toDTO(Envio envio) {       // convertir entidad Envio a DTO EnvioDTO
        return new EnvioDTO(
                envio.getId_envio(),
                envio.getId_venta(),
                envio.getDireccion_envio(),
                envio.getEstado_envio(),
                envio.getFecha_envio(),
                envio.getFecha_entrega()
        );
    }

    private Envio toEntity(EnvioDTO dto) {      // convertir DTO EnvioDTO a entidad Envio
        Envio envio = new Envio();
        envio.setId_envio(dto.getId_envio());
        envio.setId_venta(dto.getId_venta());
        envio.setDireccion_envio(dto.getDireccion_envio());
        envio.setEstado_envio(dto.getEstado_envio());
        envio.setFecha_envio(dto.getFecha_envio());
        envio.setFecha_entrega(dto.getFecha_entrega());
        return envio;
    }

    public EnvioDTO crear(EnvioDTO dto) {       // crear un nuevo envio
        Envio envio = toEntity(dto);
        return toDTO(envioRepository.save(envio));
    }

    public List<EnvioDTO> listar() {        // listar todos los envíos
        return envioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public EnvioDTO obtenerPorId(Integer id) {  // obtener un envio por su ID
        Optional<Envio> envio = envioRepository.findById(id);
        if (envio.isPresent()) {
            return toDTO(envio.get());
        } else {
            return null;
        }
    }

    public EnvioDTO actualizar(Integer id, EnvioDTO dto) {      // actualizar un envio existente
        Envio existente = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));

        existente.setId_venta(dto.getId_venta());
        existente.setDireccion_envio(dto.getDireccion_envio());
        existente.setEstado_envio(dto.getEstado_envio());
        existente.setFecha_envio(dto.getFecha_envio());
        existente.setFecha_entrega(dto.getFecha_entrega());

        return toDTO(envioRepository.save(existente));
    }

    public void eliminar(Integer id) {      // eliminar un envio por su ID
        envioRepository.deleteById(id);
    }

    
    public List<EnvioDTO> buscarPorEstado(String estado) {  // buscar envíos por estado
        return envioRepository.findAll().stream()    
                .filter(envio -> envio.getEstado_envio().equalsIgnoreCase(estado))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    
    public List<EnvioDTO> buscarPorIdVenta(Integer idVenta) {       // buscar envíos por ID de venta
        return envioRepository.findAll().stream()
                .filter(envio -> envio.getId_venta().equals(idVenta))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
