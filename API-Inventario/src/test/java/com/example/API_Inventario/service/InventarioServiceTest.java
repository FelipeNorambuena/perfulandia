package com.example.API_Inventario.service;

import com.example.API_Inventario.dto.InventarioDTO;
import com.example.API_Inventario.models.Inventario;
import com.example.API_Inventario.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para InventarioService usando Mockito
 * 
 * @ExtendWith(MockitoExtension.class) - Habilita Mockito para JUnit 5
 * @Mock - Crea un objeto simulado (mock)
 * @InjectMocks - Inyecta los mocks en la clase bajo prueba
 */
@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    // MOCK: Simula el repository (no accede a base de datos real)
    @Mock
    private InventarioRepository inventarioRepository;

    // INJECT MOCKS: Inyecta los mocks en el service
    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inventario;
    private InventarioDTO inventarioDTO;

    @BeforeEach
    void setUp() {
        // Datos de prueba
        inventario = new Inventario();
        inventario.setId_inventario(1);
        inventario.setId_producto(1);
        inventario.setStock_disponible(10);
        inventario.setUbicacion_bodega("A1");

        inventarioDTO = new InventarioDTO();
        inventarioDTO.setId_inventario(1);
        inventarioDTO.setId_producto(1);
        inventarioDTO.setStock_disponible(10);
        inventarioDTO.setUbicacion_bodega("A1");
    }

    @Test
    void testCrear_DeberiaCrearInventarioExitosamente() {
        // ARRANGE (Preparar)
        // Configuramos qué debe devolver el mock cuando se llame save()
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

        // ACT (Actuar)
        // Ejecutamos el método que queremos probar
        InventarioDTO resultado = inventarioService.crear(inventarioDTO);

        // ASSERT (Verificar)
        // Verificamos que el resultado sea correcto
        assertNotNull(resultado);
        assertEquals(1, resultado.getId_producto());
        assertEquals(10, resultado.getStock_disponible());
        
        // Verificamos que se llamó al repository exactamente 1 vez
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void testObtenerPorId_DeberiaRetornarInventarioExistente() {
        // ARRANGE
        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));

        // ACT
        InventarioDTO resultado = inventarioService.obtenerPorId(1);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(1, resultado.getId_inventario());
        assertEquals(1, resultado.getId_producto());
        
        // Verificamos que se llamó findById con el ID correcto
        verify(inventarioRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerPorId_DeberiaLanzarExcepcionSiNoExiste() {
        // ARRANGE
        // Simulamos que no se encuentra el inventario
        when(inventarioRepository.findById(999)).thenReturn(Optional.empty());

        // ACT & ASSERT
        // Verificamos que se lance una excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inventarioService.obtenerPorId(999);
        });

        assertEquals("Inventario no encontrado", exception.getMessage());
        verify(inventarioRepository, times(1)).findById(999);
    }

    @Test
    void testListar_DeberiaRetornarListaDeInventarios() {
        // ARRANGE
        List<Inventario> inventarios = Arrays.asList(inventario);
        when(inventarioRepository.findAll()).thenReturn(inventarios);

        // ACT
        List<InventarioDTO> resultado = inventarioService.listar();

        // ASSERT
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId_producto());
        
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void testActualizar_DeberiaActualizarInventarioExistente() {
        // ARRANGE
        InventarioDTO datosActualizacion = new InventarioDTO();
        datosActualizacion.setId_producto(2);
        datosActualizacion.setStock_disponible(20);

        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

        // ACT
        InventarioDTO resultado = inventarioService.actualizar(1, datosActualizacion);

        // ASSERT
        assertNotNull(resultado);
        verify(inventarioRepository, times(1)).findById(1);
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void testEliminar_DeberiaEliminarInventarioExistente() {
        // ARRANGE
        // No necesitamos mock para findById porque el método eliminar() no lo usa
        doNothing().when(inventarioRepository).deleteById(1);

        // ACT
        inventarioService.eliminar(1);

        // ASSERT
        // Solo verificamos que se llamó deleteById, que es lo que realmente hace el método
        verify(inventarioRepository, times(1)).deleteById(1);
        // No verificamos findById porque el método actual no lo llama
    }
}
