package modeloHibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private String fechaPrestamo, fechaDevolucion, multa;
	private int idLibroPrestamo, idUsuarioPrestamo;
	
	public Prestamo() {
		
	}

	public Prestamo(String fechaPrestamo, String fechaDevolucion, String multa, int idLibroPrestamo,int idUsuarioPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.multa = multa;
		this.idLibroPrestamo = idLibroPrestamo;
		this.idUsuarioPrestamo = idUsuarioPrestamo;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public int getIdLibroPrestamo() {
		return idLibroPrestamo;
	}

	public void setIdLibroPrestamo(int idLibroPrestamo) {
		this.idLibroPrestamo = idLibroPrestamo;
	}

	public int getIdUsuarioPrestamo() {
		return idUsuarioPrestamo;
	}

	public void setIdUsuarioPrestamo(int idUsuarioPrestamo) {
		this.idUsuarioPrestamo = idUsuarioPrestamo;
	}

	@Override
	public String toString() {
		return "Prestamo [fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", multa=" + multa
				+ ", idLibroPrestamo=" + idLibroPrestamo + ", idUsuarioPrestamo=" + idUsuarioPrestamo + "]";
	}
}
