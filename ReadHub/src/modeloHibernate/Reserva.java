package modeloHibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private String fechaReserva, estado;
	private int idLibroReserva, idUsuarioReserva;
	
	public Reserva() {
		
	}

	public Reserva(String fechaReserva, String estado, int idLibroReserva, int idUsuarioReserva) {
		this.fechaReserva = fechaReserva;
		this.estado = estado;
		this.idLibroReserva = idLibroReserva;
		this.idUsuarioReserva = idUsuarioReserva;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdLibroReserva() {
		return idLibroReserva;
	}

	public void setIdLibroReserva(int idLibroReserva) {
		this.idLibroReserva = idLibroReserva;
	}

	public int getIdUsuarioReserva() {
		return idUsuarioReserva;
	}

	public void setIdUsuarioReserva(int idUsuarioReserva) {
		this.idUsuarioReserva = idUsuarioReserva;
	}

	@Override
	public String toString() {
		return "Reserva [fechaReserva=" + fechaReserva + ", estado=" + estado + ", idLibroReserva=" + idLibroReserva
				+ ", idUsuarioReserva=" + idUsuarioReserva + "]";
	}

}
