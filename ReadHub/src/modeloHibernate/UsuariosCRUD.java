package modeloHibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UsuariosCRUD {
    private final Session session;

    public UsuariosCRUD(final Session session) {
        this.session = session;
    }

    public boolean registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono) {
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

    public List<Usuario> obtenerTodosUsuarios() {
        try {
            String hql = "FROM Usuario";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        try {
            return session.get(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminarUsuario(int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.delete(usuario);
                transaction.commit();
                return true;
            } else {
                System.out.println("Usuario no encontrado.");
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false; //false
        }
    }
}