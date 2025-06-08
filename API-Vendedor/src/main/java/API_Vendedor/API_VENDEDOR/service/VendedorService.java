package API_Vendedor.API_VENDEDOR.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import API_Vendedor.API_VENDEDOR.dto.VendedorDTO;
import API_Vendedor.API_VENDEDOR.models.Vendedor;
import API_Vendedor.API_VENDEDOR.repository.VendedorRepositoy;

@Service
public class VendedorService {


    @Autowired
    private VendedorRepositoy repository;

    private VendedorDTO toDTO(Vendedor Vendedor){       // Conversión de entidad a DTO
        VendedorDTO dto = new VendedorDTO();
        dto.setId_vendedor(Vendedor.getId_vendedor());
        dto.setId_usuario(Vendedor.getId_usuario());
        dto.setNombre_completo(Vendedor.getNombre_completo());
        dto.setRut(Vendedor.getRut());
        dto.setArea_ventas(Vendedor.getArea_ventas());
        dto.setMeta(Vendedor.getMeta());
        return dto;
    }

     private Vendedor toEntity(VendedorDTO dto) {    // Conversión de DTO a entidad
        Vendedor Vendedor = new Vendedor();
        Vendedor.setId_vendedor(dto.getId_vendedor());
            Vendedor.setId_usuario(dto.getId_usuario());
            Vendedor.setNombre_completo(dto.getNombre_completo());
            Vendedor.setRut(dto.getRut());
            Vendedor.setArea_ventas(dto.getArea_ventas());
        return Vendedor;
    }

    public VendedorDTO createVendedor(VendedorDTO dto) {    // Método para crear un nuevo vendedor
        Vendedor vendedor = toEntity(dto);
        vendedor = repository.save(vendedor);
        return toDTO(vendedor);
    }

    public List<VendedorDTO> getAllVendedores() {    // Método para obtener todos los vendedores
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VendedorDTO getVendedorById(Integer id) {    // Método para obtener un vendedor por ID
        Vendedor vendedor = repository.findById(id).orElse(null);
        return vendedor != null ? toDTO(vendedor) : null;
    }

    

    public List<VendedorDTO> getVendedoresPorAreaVentas(String areaVentas) {    //metodo para ver vendedores por area de ventas
        return repository.findAll().stream()
                .filter(v -> areaVentas.equalsIgnoreCase(v.getArea_ventas()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getMetasPorSucursal(String areaVentas) {  // Método para obtener metas por sucursal
    return repository.findAll().stream()
            .filter(v -> areaVentas.equalsIgnoreCase(v.getArea_ventas()))
            .map(v -> {
                Map<String, Object> metaInfo = new HashMap<>();
                metaInfo.put("id_vendedor", v.getId_vendedor());
                metaInfo.put("nombre_completo", v.getNombre_completo());
                metaInfo.put("meta", v.getMeta());
                return metaInfo;
            })
            .collect(Collectors.toList());
}

    public VendedorDTO actualizarMetaVendedor(Integer id, VendedorDTO dto) {
    Optional<Vendedor> optionalVendedor = repository.findById(id);
    if (optionalVendedor.isPresent()) {
        Vendedor vendedor = optionalVendedor.get();
        vendedor.setMeta(dto.getMeta()); // Asume que getMeta() devuelve Integer
        vendedor = repository.save(vendedor);
        return toDTO(vendedor);
    }
    return null; // O lanzar una excepción si prefieres
}

    public VendedorDTO updateVendedor(Integer id, VendedorDTO dto) {    // Método para actualizar un vendedor
        if (repository.existsById(id)) {
            Vendedor vendedor = toEntity(dto);
            vendedor.setId_vendedor(id);  // Asegurarse de que el ID se mantenga
            vendedor = repository.save(vendedor);
            return toDTO(vendedor);
        }
        return null;  // O lanzar una excepción si no existe
    }

    public void deleteVendedor(Integer id) {    // Método para eliminar un vendedor
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }       



    
}
