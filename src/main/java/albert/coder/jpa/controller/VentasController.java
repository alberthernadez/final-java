package albert.coder.jpa.controller;

// Asegúrate de tener el servicio VentaService importado
import albert.coder.jpa.entities.Venta;
import albert.coder.jpa.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")

public class VentasController {

    @Autowired
    private VentaService ventaService;  // Inyecta el servicio de venta

    // Obtener todas las ventas
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.findAll();  // Devuelve todas las ventas
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Optional<Venta> venta = ventaService.findById(id);  // Busca la venta por su ID
        return venta.map(ResponseEntity::ok)  // Si la venta existe, devuelve una respuesta OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devuelve 404 Not Found
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.save(venta);  // Guarda la nueva venta
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);  // Devuelve la venta creada con el estado 201
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        Optional<Venta> venta = ventaService.findById(id);  // Busca la venta por ID
        if (venta.isPresent()) {  // Si la venta existe, la actualiza
            Venta ventaActualizada = ventaService.save(ventaDetails);
            return ResponseEntity.ok(ventaActualizada);  // Devuelve la venta actualizada con el estado 200
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra la venta, devuelve 404 Not Found
    }

    // Eliminar una venta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteById(id);  // Elimina la venta por su ID
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content, ya que la eliminación fue exitosa
    }
}
