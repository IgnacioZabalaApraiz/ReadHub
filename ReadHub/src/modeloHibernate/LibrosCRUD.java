package modeloHibernate;

import java.util.List;

import org.hibernate.Session;

public class LibrosCRUD {
	private final Session session;
	
	public LibrosCRUD(final Session session) {
		this.session = session;
	}
	
	public void insertarLibro(Libro libro) {
		session.persist(libro);
		session.getTransaction().commit();
	}
	
	public void borrarLibro(int id) {
		var libro = session.get(Libro.class, id);
		session.remove(libro);
	}
	
	public List<Libro> listarLibros() {
		return session.createQuery("FROM libro", Libro.class).getResultList();
	}
	
	public void actualizarLibro(int id, int newStock) {
		var libro = session.get(Libro.class, id);
		libro.setDisponibilidad(newStock);
		session.merge(libro);
	}

}
