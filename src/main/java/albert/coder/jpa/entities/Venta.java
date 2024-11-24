package albert.coder.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")

public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total = 0.0;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VentaDetalle> detalles = new ArrayList<>();

    public Venta() {
        this.fecha = LocalDateTime.now();
    }

    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
    }

    public void addDetalle(VentaDetalle detalle) {
        detalles.add(detalle);
        detalle.setVenta(this);
        total += detalle.getSubtotal();
    }

    public void removeDetalle(VentaDetalle detalle) {
        detalles.remove(detalle);
        detalle.setVenta(null);
        total -= detalle.getSubtotal();
    }

    public void calculateTotal() {
        total = detalles.stream()
                .mapToDouble(VentaDetalle::getSubtotal)
                .sum();
    }

    public Double getTotal() {
        return total;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    // Getters y Setters manuales si no usas Lombok
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

}
