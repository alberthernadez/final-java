package albert.coder.jpa.repository;

import albert.coder.jpa.entities.Producto;

import java.util.List;

public class ProductoRepository {
    public List<Producto> findByNombre(String nombre) {

        return null;
    }
    public List<Producto> findByPrecioBetween(Double minPrice, Double maxPrice) {
        return null;
    }

    public List<Producto> findByNombreContaining(String term) {
        return null;
    }

}
