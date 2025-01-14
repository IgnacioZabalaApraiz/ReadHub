package modeloHibernate;

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
		
	}

}
