package modeloHibernate;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PrestamoCRUD {
    private final Session session;

    public PrestamoCRUD(final Session session) {
        this.session = session;
    }

    public void prestarLibro(Long idLibro, Long idUsuario, Date fechaPrestamo, Date fechaDevolucion) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Crear un nuevo préstamo
            Prestamo prestamo = new Prestamo();

            // Obtener entidades relacionadas (Libro y Usuario)
            Libro libro = session.get(Libro.class, idLibro);
            Usuario usuario = session.get(Usuario.class, idUsuario);

            if (libro != null && usuario != null) {
                prestamo.setLibro(libro);
                prestamo.setUsuario(usuario);
                prestamo.setFechaPrestamo(fechaPrestamo);
                prestamo.setFechaDevolucion(fechaDevolucion);
                prestamo.setMulta(0.0f); // Inicialmente sin multa

                // Guardar el préstamo
                session.persist(prestamo);
                transaction.commit();
            } else {
                System.out.println("El libro o usuario no existe.");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void actualizarPrestamo(Long idPrestamo, Date nuevaFechaDevolucion, Float nuevaMulta) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Obtener el préstamo y actualizar los campos necesarios
            Prestamo prestamo = session.get(Prestamo.class, idPrestamo);
            if (prestamo != null) {
                prestamo.setFechaDevolucion(nuevaFechaDevolucion);
                prestamo.setMulta(nuevaMulta);
                session.merge(prestamo);

                transaction.commit();
            } else {
                System.out.println("El préstamo no existe.");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
