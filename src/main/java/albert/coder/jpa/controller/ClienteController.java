package albert.coder.jpa.controller;

// Asegúrate de que el servicio ClienteService esté importado
import albert.coder.jpa.entities.Cliente;
import albert.coder.jpa.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class ClienteController {


    @Autowired
    private ClienteService clienteService;  // Inyecta el servicio de cliente

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();  // Llama al método findAll() del servicio
    }

    // Obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);  // Llama al método findById() del servicio
        return cliente.map(ResponseEntity::ok)  // Si el cliente existe, devuelve una respuesta OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devuelve 404 Not Found
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.save(cliente);  // Guarda el nuevo cliente
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);  // Devuelve el cliente creado con el estado 201
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> cliente = clienteService.findById(id);  // Busca el cliente por ID
        if (cliente.isPresent()) {  // Si el cliente existe, lo actualiza
            Cliente clienteActualizado = clienteService.save(clienteDetails);
            return ResponseEntity.ok(clienteActualizado);  // Devuelve el cliente actualizado con el estado 200
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el cliente, devuelve 404 Not Found
    }

    // Eliminar un cliente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);  // Elimina el cliente por su ID
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content, ya que la eliminación fue exitosa
    }

}
