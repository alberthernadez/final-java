package albert.coder.jpa.controller;
 // Asegúrate de tener el servicio ProductoService importado
import albert.coder.jpa.entities.Producto;
import albert.coder.jpa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")


public class ProductoController {


    @Autowired
    private ProductoService productoService;  // Inyecta el servicio de producto

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();  // Devuelve todos los productos
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);  // Busca el producto por su ID
        return producto.map(ResponseEntity::ok)  // Si el producto existe, devuelve una respuesta OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devuelve 404 Not Found
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);  // Guarda el nuevo producto
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);  // Devuelve el producto creado con el estado 201
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Optional<Producto> producto = productoService.findById(id);  // Busca el producto por ID
        if (producto.isPresent()) {  // Si el producto existe, lo actualiza
            Producto productoActualizado = productoService.save(productoDetails);
            return ResponseEntity.ok(productoActualizado);  // Devuelve el producto actualizado con el estado 200
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el producto, devuelve 404 Not Found
    }

    // Eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);  // Elimina el producto por su ID
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content, ya que la eliminación fue exitosa
    }

}
