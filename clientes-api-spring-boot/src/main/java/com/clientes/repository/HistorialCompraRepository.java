package com.clientes.repository;

import com.clientes.models.HistorialCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialCompraRepository extends JpaRepository<HistorialCompra, Integer> {
    List<HistorialCompra> findByIdCliente(Integer idCliente);
}
