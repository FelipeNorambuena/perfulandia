package com.example.API_Venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API_Venta.models.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    

    
} 
