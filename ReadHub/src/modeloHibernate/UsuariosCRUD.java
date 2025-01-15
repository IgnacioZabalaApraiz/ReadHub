package modeloHibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import modeloHibernate.Usuario.Rol;

public class UsuariosCRUD {
	private final Session session;
	
	public UsuariosCRUD(final Session session) {
		this.session = session;
	}
	
	// Faltaria las validaciones a la hora de registrar
	public void registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono) {
		final var usuario = new Usuario(nombre, apellidos, contrasena, email, dni, telefono, Rol.usuario);
		session.persist(usuario);
	}
	
	public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
		try {
            // Crear la consulta HQL para buscar al usuario
            String hql = "FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasena", contrasena);

            // Obtener el resultado único (o null si no existe)
            Usuario usuario = query.uniqueResult();

            return usuario; // Será null si no se encuentra
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
	}

}
