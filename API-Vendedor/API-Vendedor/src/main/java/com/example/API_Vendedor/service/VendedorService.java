package com.example.API_Vendedor.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.API_Vendedor.dto.VendedorDTO;
import com.example.API_Vendedor.models.Vendedor;
import com.example.API_Vendedor.repository.VendedorRepository;

@Service
public class VendedorService {


    @Autowired
    private VendedorRepository repository;

    public VendedorDTO guardar(VendedorDTO dto) { // Validación de datos
        Vendedor vendedor = toEntity(dto);
        Vendedor saved = repository.save(vendedor);
        return toDTO(saved);
    }


    public List<VendedorDTO> listar() {     // Validación de datos
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VendedorDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<VendedorDTO> actualizar(Integer id, VendedorDTO dto) {
        return repository.findById(id).map(Vendedor -> {
            Vendedor.setId_vendedor(dto.getId_vendedor());
            Vendedor.setId_usuario(dto.getId_usuario());
            Vendedor.setNombre_completo(dto.getNombre_completo());
            Vendedor.setRut(dto.getRut());
            Vendedor.setArea_ventas(dto.getArea_ventas());
            return toDTO(repository.save(Vendedor));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos auxiliares
    private VendedorDTO toDTO(Vendedor Vendedor) {
        VendedorDTO dto = new VendedorDTO();
        dto.setId_vendedor(Vendedor.getId_vendedor());;
        dto.setId_usuario(Vendedor.getId_usuario());;
        dto.setNombre_completo(Vendedor.getNombre_completo());;
        dto.setRut(Vendedor.getRut());
        dto.setArea_ventas(Vendedor.getArea_ventas());;
        return dto;
    }

    private Vendedor toEntity(VendedorDTO dto) {
        Vendedor Vendedor = new Vendedor();
        Vendedor.setId_vendedor(dto.getId_vendedor());
            Vendedor.setId_usuario(dto.getId_usuario());
            Vendedor.setNombre_completo(dto.getNombre_completo());
            Vendedor.setRut(dto.getRut());
            Vendedor.setArea_ventas(dto.getArea_ventas());
        return Vendedor;
    }

}
