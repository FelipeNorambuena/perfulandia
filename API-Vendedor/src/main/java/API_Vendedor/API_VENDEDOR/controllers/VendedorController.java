package API_Vendedor.API_VENDEDOR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import API_Vendedor.API_VENDEDOR.dto.VendedorDTO;
import API_Vendedor.API_VENDEDOR.service.VendedorService;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService service;

    // Crear un nuevo vendedor
    @PostMapping
    public VendedorDTO createVendedor(@RequestBody VendedorDTO dto) {
        return service.createVendedor(dto);
    }

    // Obtener todos los vendedores
    @GetMapping
    public List<VendedorDTO> getAllVendedores() {
        return service.getAllVendedores();
    }

    // Obtener un vendedor por ID
    @GetMapping("/{id}")
    public VendedorDTO getVendedorById(@PathVariable Integer id) {
        return service.getVendedorById(id);
    }

    // Actualizar un vendedor
    @PutMapping("/{id}")
    public VendedorDTO updateVendedor(@PathVariable Integer id, @RequestBody VendedorDTO dto) {
        return service.updateVendedor(id, dto);
    }

    // Actualizar solo la meta de un vendedor
    @PutMapping("/{id}/meta")
    public VendedorDTO actualizarMetaVendedor(@PathVariable Integer id, @RequestBody VendedorDTO dto) {
        return service.actualizarMetaVendedor(id, dto);
    }

    // Eliminar un vendedor
    @DeleteMapping("/{id}")
    public void deleteVendedor(@PathVariable Integer id) {
        service.deleteVendedor(id);
    }

    // Obtener vendedores por área de ventas (sucursal)
    @GetMapping("/area/{areaVentas}")
    public List<VendedorDTO> getVendedoresPorAreaVentas(@PathVariable String areaVentas) {
        return service.getVendedoresPorAreaVentas(areaVentas);
    }

    // Obtener metas por sucursal (área de ventas)
    @GetMapping("/metas/{areaVentas}")
    public List<Map<String, Object>> getMetasPorSucursal(@PathVariable String areaVentas) {
        return service.getMetasPorSucursal(areaVentas);
    }
}
