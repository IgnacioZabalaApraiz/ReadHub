package servicio;

import modeloHibernate.Libro;
import modeloHibernate.LibrosCRUD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LibroServiceImpl implements LibroService {
    private SessionFactory sessionFactory;
    private LibrosCRUD librosCRUD;

    public LibroServiceImpl() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            librosCRUD = new LibrosCRUD(session);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<Libro> getAllLibros() {
        return librosCRUD.listarLibros();
    }

    @Override
    public void saveLibro(Libro libro) {
        librosCRUD.insertarLibro(libro);
    }

    @Override
    public void updateLibroDisponibilidad(Libro libro) {
        librosCRUD.actualizarDisponibilidadLibro(libro.getIdLibro(), libro.getDisponibilidad());
    }

    @Override
    public void deleteLibro(Libro libro) {
        librosCRUD.borrarLibro(libro.getIdLibro());
    }

    @Override
    public Libro getLibroByTitulo(String titulo) {
        // This method is not directly supported by LibrosCRUD
        // We need to implement it by filtering the list of all books
        return getAllLibros().stream()
                .filter(libro -> libro.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
    }
}

