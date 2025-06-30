package API_Vendedor.API_VENDEDOR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    // MÉTODOS HATEOAS

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<VendedorDTO> obtenerHATEOAS(@PathVariable Integer id) {
        VendedorDTO dto = service.getVendedorById(id);
        dto.add(linkTo(methodOn(VendedorController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(VendedorController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(VendedorController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        dto.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + dto.getId_vendedor()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + dto.getId_vendedor()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + dto.getId_vendedor()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/hateoas")
    public ResponseEntity<List<VendedorDTO>> obtenerTodosHATEOAS() {
        List<VendedorDTO> lista = service.getAllVendedores();
        for (VendedorDTO dto : lista) {
            dto.add(linkTo(methodOn(VendedorController.class).obtenerHATEOAS(dto.getId_vendedor())).withSelfRel());
            dto.add(Link.of("http://localhost:8888/api/proxy/vendedores").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + dto.getId_vendedor()).withRel("Crear HATEOAS").withType("POST"));
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/hateoas")
    public ResponseEntity<VendedorDTO> crearHATEOAS(@RequestBody VendedorDTO dto) {
        VendedorDTO creado = service.createVendedor(dto);
        creado.add(linkTo(methodOn(VendedorController.class).obtenerHATEOAS(creado.getId_vendedor())).withSelfRel());
        creado.add(linkTo(methodOn(VendedorController.class).obtenerTodosHATEOAS()).withRel("todos"));
        creado.add(linkTo(methodOn(VendedorController.class).actualizarHATEOAS(creado.getId_vendedor(), creado)).withRel("actualizar"));
        creado.add(linkTo(methodOn(VendedorController.class).eliminarHATEOAS(creado.getId_vendedor())).withRel("eliminar"));
        creado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + creado.getId_vendedor()).withSelfRel());
        creado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + creado.getId_vendedor()).withRel("Modificar HATEOAS").withType("PUT"));
        creado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + creado.getId_vendedor()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/hateoas/{id}")
    public ResponseEntity<VendedorDTO> actualizarHATEOAS(@PathVariable Integer id, @RequestBody VendedorDTO dto) {
        VendedorDTO actualizado = service.updateVendedor(id, dto);
        actualizado.add(linkTo(methodOn(VendedorController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(VendedorController.class).obtenerTodosHATEOAS()).withRel("todos"));
        actualizado.add(linkTo(methodOn(VendedorController.class).eliminarHATEOAS(id)).withRel("eliminar"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + actualizado.getId_vendedor()).withSelfRel());
        actualizado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + actualizado.getId_vendedor()).withRel("Modificar HATEOAS").withType("PUT"));
        actualizado.add(Link.of("http://localhost:8888/api/proxy/vendedores/" + actualizado.getId_vendedor()).withRel("Eliminar HATEOAS").withType("DELETE"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/hateoas/{id}")
    public ResponseEntity<VendedorDTO> eliminarHATEOAS(@PathVariable Integer id) {
        service.deleteVendedor(id);
        VendedorDTO eliminado = new VendedorDTO();
        eliminado.setId_vendedor(id);
        eliminado.add(linkTo(methodOn(VendedorController.class).obtenerTodosHATEOAS()).withRel("todos"));
        eliminado.add(linkTo(methodOn(VendedorController.class).crearHATEOAS(null)).withRel("crear"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/vendedores").withRel("Get todos HATEOAS"));
        eliminado.add(Link.of("http://localhost:8888/api/proxy/vendedores").withRel("Crear HATEOAS").withType("POST"));
        return ResponseEntity.ok(eliminado);
    }
}
