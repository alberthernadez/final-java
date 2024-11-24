package albert.coder.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")  // Define la tabla en la base de datos
@Getter
@Setter

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)  // El DNI no puede ser nulo y debe ser único
    private String dni;

    private String nombre;
    private String email;
    private String telefono;


}
