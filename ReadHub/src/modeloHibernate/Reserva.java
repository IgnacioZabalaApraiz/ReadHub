package modeloHibernate;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_libro_reserva")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario_reserva")
    private Usuario usuario;

    @Column(name = "fecha_reserva", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;

    @Column
    private boolean estado;

    // Constructors, getters, and setters
    // ...
}

