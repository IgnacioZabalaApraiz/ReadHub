	package modeloHibernate;
	
	import java.util.List;
	import org.hibernate.Session;
	import org.hibernate.Transaction;
	
	public class LibrosCRUD {
	    private final Session session;
	    
	    public LibrosCRUD(final Session session) {
	        this.session = session;
	    }
	    
	    public void insertarLibro(Libro libro) {
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            session.persist(libro);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    public void borrarLibro(Long id) {
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            Libro libro = session.get(Libro.class, id);
	            if (libro != null) {
	                session.remove(libro);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	    
	    public List<Libro> listarLibros() {
	        return session.createQuery("FROM Libro", Libro.class).getResultList();
	    }
	    
	    public void actualizarDisponibilidadLibro(Long id, boolean disponibilidad) {
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            Libro libro = session.get(Libro.class, id);
	            if (libro != null) {
	                libro.setDisponibilidad(disponibilidad);
	                session.merge(libro);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	}
	
