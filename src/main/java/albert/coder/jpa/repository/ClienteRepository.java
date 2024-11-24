package albert.coder.jpa.repository;

import albert.coder.jpa.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public abstract class ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByNombre(String nombre);

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByNombreContaining(String term);

}
