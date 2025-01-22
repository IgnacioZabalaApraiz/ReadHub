package modeloHibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuariosCRUD {
    private final Session session;

    public UsuariosCRUD(final Session session) {
        this.session = session;
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono) {
        // Validaciones básicas
        if (nombre == null || nombre.isEmpty() ||
            apellidos == null || apellidos.isEmpty() ||
            contrasena == null || contrasena.isEmpty() ||
            email == null || email.isEmpty()) {
            System.out.println("Error: Algunos campos obligatorios están vacíos.");
            return false;
        }

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Usuario usuario = new Usuario(nombre, apellidos, contrasena, email, dni, telefono);
            session.persist(usuario);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Método para iniciar sesión
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        try {
            String hql = "FROM Usuario u WHERE u.nombre = :nombreUsuario AND u.contrasena = :contrasena";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasena", contrasena);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
