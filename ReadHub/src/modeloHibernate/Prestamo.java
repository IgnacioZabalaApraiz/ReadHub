package modeloHibernate;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long idPrestamo;

    @ManyToOne
    @JoinColumn(name = "id_libro_prestamo")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario_prestamo")
    private Usuario usuario;

    @Column(name = "fecha_prestamo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;

    @Column(name = "fecha_devolucion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;

    @Column
    private Float multa;

    // Constructors, getters, and setters
    // ...
}

