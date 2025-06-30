package com.example.API_Inventario.controllers;

import com.example.API_Inventario.dto.InventarioDTO;
import com.example.API_Inventario.service.InventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de integración para InventarioController usando Mockito
 * 
 * @WebMvcTest - Configura solo el contexto web (controllers)
 * @MockitoBean - Crea un mock bean de Spring (nueva anotación)
 * MockMvc - Simula peticiones HTTP sin levantar servidor
 */
@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula peticiones HTTP

    @MockitoBean
    private InventarioService inventarioService; // Mock del service

    @Autowired
    private ObjectMapper objectMapper; // Para convertir JSON

    private InventarioDTO inventarioDTO;

    @BeforeEach
    void setUp() {
        inventarioDTO = new InventarioDTO();
        inventarioDTO.setId_inventario(1);
        inventarioDTO.setId_producto(1);
        inventarioDTO.setStock_disponible(10);
        inventarioDTO.setUbicacion_bodega("A1");
    }

    @Test
    void testCrear_DeberiaCrearInventarioExitosamente() throws Exception {
        // ARRANGE
        when(inventarioService.crear(any(InventarioDTO.class))).thenReturn(inventarioDTO);

        // ACT & ASSERT
        mockMvc.perform(post("/api/inventario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_inventario").value(1))
                .andExpect(jsonPath("$.id_producto").value(1))
                .andExpect(jsonPath("$.stock_disponible").value(10))
                .andExpect(jsonPath("$.ubicacion_bodega").value("A1"));

        // Verificamos que se llamó al service
        verify(inventarioService, times(1)).crear(any(InventarioDTO.class));
    }

    @Test
    void testListar_DeberiaRetornarListaDeInventarios() throws Exception {
        // ARRANGE
        List<InventarioDTO> inventarios = Arrays.asList(inventarioDTO);
        when(inventarioService.listar()).thenReturn(inventarios);

        // ACT & ASSERT
        mockMvc.perform(get("/api/inventario")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id_inventario").value(1))
                .andExpect(jsonPath("$[0].stock_disponible").value(10));

        verify(inventarioService, times(1)).listar();
    }

    @Test
    void testObtener_DeberiaRetornarInventarioPorId() throws Exception {
        // ARRANGE
        when(inventarioService.obtenerPorId(1)).thenReturn(inventarioDTO);

        // ACT & ASSERT
        mockMvc.perform(get("/api/inventario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_inventario").value(1))
                .andExpect(jsonPath("$.stock_disponible").value(10));

        verify(inventarioService, times(1)).obtenerPorId(1);
    }

    @Test
    void testActualizar_DeberiaActualizarInventarioExitosamente() throws Exception {
        // ARRANGE
        InventarioDTO datosActualizacion = new InventarioDTO();
        datosActualizacion.setStock_disponible(20);

        when(inventarioService.actualizar(eq(1), any(InventarioDTO.class))).thenReturn(inventarioDTO);

        // ACT & ASSERT
        mockMvc.perform(put("/api/inventario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datosActualizacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_inventario").value(1));

        verify(inventarioService, times(1)).actualizar(eq(1), any(InventarioDTO.class));
    }

    @Test
    void testEliminar_DeberiaEliminarInventarioExitosamente() throws Exception {
        // ARRANGE
        // No need to mock void methods unless they throw exceptions

        // ACT & ASSERT
        mockMvc.perform(delete("/api/inventario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(inventarioService, times(1)).eliminar(1);
    }

    @Test
    void testObtenerHATEOAS_DeberiaRetornarInventarioConLinks() throws Exception {
        // ARRANGE
        when(inventarioService.obtenerPorId(1)).thenReturn(inventarioDTO);

        // ACT & ASSERT
        mockMvc.perform(get("/api/inventario/hateoas/1")
                .accept("application/hal+json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_inventario").value(1))
                .andExpect(jsonPath("$._links").exists());

        verify(inventarioService, times(1)).obtenerPorId(1);
    }
}
