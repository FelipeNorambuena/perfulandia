package com.clientes.repository;

import com.clientes.models.HistorialCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialCompraRepository extends JpaRepository<HistorialCompra, Integer> { //este repository cumple la funcion de CRUD para la entidad HistorialCompra
    List<HistorialCompra> findByIdCliente(Integer idCliente);       // Busca historial de compras por idCliente
}
