package albert.coder.jpa.repository;

import albert.coder.jpa.entities.Cliente;
import albert.coder.jpa.entities.Producto;
import albert.coder.jpa.entities.Venta;

import java.util.Date;
import java.util.List;

public class VentaRepository {

    public List<Venta> findByCliente(Cliente cliente) {
        // Lógica para buscar ventas por cliente
        return null;
    }

    public List<Venta> findByProducto(Producto producto) {
        // Lógica para buscar ventas por producto
        return null;
    }

    public List<Venta> findByFechaBetween(Date startDate, Date endDate) {
        // Lógica para buscar ventas entre dos fechas
        return null;
    }

    public List<Venta> findByClienteAndProducto(Cliente cliente, Producto producto) {
        // Lógica para buscar ventas por cliente y producto
        return null;
    }
}
