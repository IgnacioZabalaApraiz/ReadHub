package modeloHibernate;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PrestamoCRUD {
    private final Session session;

    public PrestamoCRUD(final Session session) {
        this.session = session;
    }

    public void prestarLibro(Long idLibro, Long idUsuario, Date fechaPrestamo) {
        Transaction transaction = null;
        try {
        	if (!session.getTransaction().isActive()) {
        	    transaction = session.beginTransaction();
        	} else {
        		transaction = session.getTransaction();
        	}

            // Crear un nuevo préstamo
            Prestamo prestamo = new Prestamo();

            // Obtener entidades relacionadas (Libro y Usuario)
            Libro libro = session.get(Libro.class, idLibro);
            Usuario usuario = session.get(Usuario.class, idUsuario);

            if (libro != null && usuario != null) {
                prestamo.setLibro(libro);
                prestamo.setUsuario(usuario);
                prestamo.setFechaPrestamo(fechaPrestamo);
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
    
    public boolean isBookBorrowedByUser(Long idLibro, Long idUsuario) {
        try {
            String hql = "FROM Prestamo p WHERE p.libro.idLibro = :idLibro AND p.usuario.idUsuario = :idUsuario AND p.fechaDevolucion IS NULL";
            return session.createQuery(hql, Prestamo.class)
                    .setParameter("idLibro", idLibro)
                    .setParameter("idUsuario", idUsuario)
                    .uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void devolverLibro(Long idLibro, Long idUsuario, Date fechaDevolucion) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String hql = "FROM Prestamo p WHERE p.libro.idLibro = :idLibro AND p.usuario.idUsuario = :idUsuario AND p.fechaDevolucion IS NULL";
            Prestamo prestamo = session.createQuery(hql, Prestamo.class)
                    .setParameter("idLibro", idLibro)
                    .setParameter("idUsuario", idUsuario)
                    .uniqueResult();

            if (prestamo != null) {
                prestamo.setFechaDevolucion(fechaDevolucion);
                
                // Aquí puedes añadir lógica para calcular multas si es necesario
                // Por ejemplo:
                // long diasPrestamo = ChronoUnit.DAYS.between(prestamo.getFechaPrestamo().toInstant(), fechaDevolucion.toInstant());
                // if (diasPrestamo > 14) { // Si el préstamo excede 14 días
                //     float multa = (diasPrestamo - 14) * 0.5f; // 0.5 por día de retraso
                //     prestamo.setMulta(multa);
                // }

                session.merge(prestamo);
                
                // Actualizar la disponibilidad del libro
                Libro libro = prestamo.getLibro();
                libro.setDisponibilidad(true);
                session.merge(libro);

                transaction.commit();
            } else {
                System.out.println("No se encontró un préstamo activo para este libro y usuario.");
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
