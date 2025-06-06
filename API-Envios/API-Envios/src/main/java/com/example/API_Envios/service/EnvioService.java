package com.example.API_Envios.service;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.API_Envios.models.Envio;
import com.example.API_Envios.repository.EnvioRepository;



@Service
public class EnvioService {
    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {  // Constructor injection for EnvioRepository
        this.envioRepository = envioRepository;
    }
    
    public Envio createEnvio(Envio envio) {
        return envioRepository.save(envio);  // guardar el envio en la base de datos
    }

    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();  // obtener todos los envios de la base de datos
    }

    public ResponseEntity<?> getEnvioById(Integer id) {  // obtener un envio por su ID
        Optional<Envio> envio = envioRepository.findById(id);
        if (envio.isPresent()) {
            return ResponseEntity.ok(envio.get());
        } else {
            return ResponseEntity.status(404).body("Envío no encontrado");
        }
    }

    public Envio updateEnvio(Integer id, Envio envioDetails) {      // actualizar un envio existente
        Envio envio = envioRepository.findById(id).orElse(null);
        if (envio != null) {
            envio.setId_venta(envioDetails.getId_venta());
            envio.setDireccion_envio(envioDetails.getDireccion_envio());
            envio.setFecha_envio(envioDetails.getFecha_envio());
            envio.setFecha_entrega(envioDetails.getFecha_entrega());
            return envioRepository.save(envio);  // actualizar el envio en la base de datos
        } else {
            return null; 
        }
    }

    public void deleteEnvio(Integer id) {
        envioRepository.deleteById(id);  // eliminar un envio por su ID
    }

    public List<Envio> getEnviosByVentaId(Integer id_venta) {
        return envioRepository.findAll().stream()
                .filter(envio -> envio.getId_venta().equals(id_venta))
                .toList();  // obtener envios por ID de venta
    }



    

}
