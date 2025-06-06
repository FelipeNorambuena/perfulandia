package com.example.API_Envios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.API_Envios.models.Envio;

@Repository
public interface EnvioRepository extends JpaRepository <Envio, Integer>{

}
