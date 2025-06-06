package com.example.API_Inventario.repository;

import com.example.API_Inventario.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
   
}
