package controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modeloHibernate.Libro;

public class PruebasHIbernate {
    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            // Abrir sesión
            try (Session session = factory.openSession()) {
                // Iniciar transacción
                session.beginTransaction();

                // Obtener el objeto Libro con ID 1
                Libro libro = session.get(Libro.class, 1);

                // Validar si el objeto fue encontrado
                if (libro != null) {
                    System.out.printf("Titulo: %s%n", libro.getTitulo());
                } else {
                    System.out.println("No se encontró el libro con ID 1.");
                }

                // Confirmar transacción (no necesaria si solo se lee)
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
