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

    // Default constructor
    public Reserva() {}

    // Constructor with fields
	public Reserva(Long idReserva, Libro libro, Usuario usuario, Date fechaReserva, boolean estado) {
		super();
		this.idReserva = idReserva;
		this.libro = libro;
		this.usuario = usuario;
		this.fechaReserva = fechaReserva;
		this.estado = estado;
	}

	// Getters and setters
	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", libro=" + libro + ", usuario=" + usuario + ", fechaReserva="
				+ fechaReserva + ", estado=" + estado + "]";
	}
    
}

