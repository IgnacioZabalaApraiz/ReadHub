package modeloHibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	private String titulo, autor, genero, fechaPublicacion, urlImagen;
	private int disponibilidad;
	
	public Libro() {
		
	}
	
	public Libro(String titulo, String autor, String genero, String fechaPublicacion, String urlImagen, int disponibilidad) {
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.fechaPublicacion = fechaPublicacion;
		this.urlImagen = urlImagen;
		this.disponibilidad = disponibilidad;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public int getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + ", fechaPublicacion="
				+ fechaPublicacion + ", urlImagen=" + urlImagen + ", disponibilidad=" + disponibilidad + "]";
	}
}
