package modeloHibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import javax.swing.JOptionPane;

import servicio.HibernateUtil;

public class UsuariosCRUD {

    public UsuariosCRUD() {
        // No need to store the session as a field
    }

    public boolean registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (apellidos == null || apellidos.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los apellidos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (contrasena == null || contrasena.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (String.valueOf(dni).length() != 8) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 8 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (String.valueOf(telefono).length() != 9) {
            JOptionPane.showMessageDialog(null, "El teléfono debe tener exactamente 9 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
        	if (!session.getTransaction().isActive()) {
        	    transaction = session.beginTransaction();
        	} else {
        		transaction = session.getTransaction();
        	}

            String hql = "FROM Usuario WHERE email = :email OR dni = :dni";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("email", email);
            query.setParameter("dni", dni);
            
            List<Usuario> usuariosExistentes = query.list();
            if (!usuariosExistentes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El email o DNI ya están registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

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
        Session session = HibernateUtil.getSession();
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
        Session session = HibernateUtil.getSession();
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
        Session session = HibernateUtil.getSession();
        try {
            return session.get(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminarUsuario(Object userId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            // Verifica si hay una transacción activa
            if (!session.getTransaction().isActive()) {
                transaction = session.beginTransaction();
            } else {
                transaction = session.getTransaction();
            }

            if (userId instanceof Long || userId instanceof Integer) {
                // Convertir el Object a Long (o Integer según sea necesario)
                Long userIdLong = (userId instanceof Long) ? (Long) userId : Long.valueOf((Integer) userId);

                // Buscar el usuario por su ID
                Usuario usuario = session.get(Usuario.class, userIdLong);
                if (usuario != null) {
                    // Eliminar usuario
                    session.delete(usuario);
                    transaction.commit();
                    return true;
                } else {
                    System.out.println("Usuario no encontrado.");
                    return false;
                }
            } else {
                System.out.println("El tipo de userId no es válido.");
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
    public boolean editarUsuario(Object userId, String nuevoNombre, String nuevoApellido, String nuevoEmail, int nuevoTelefono, String nuevoRol) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            // Verifica si hay una transacción activa
            if (!session.getTransaction().isActive()) {
                transaction = session.beginTransaction();
            } else {
                transaction = session.getTransaction();
            }

            // Validar el tipo de userId
            if (userId instanceof Long || userId instanceof Integer) {
                Long userIdLong = (userId instanceof Long) ? (Long) userId : Long.valueOf((Integer) userId);

                // Obtener el usuario por ID
                Usuario usuario = session.get(Usuario.class, userIdLong);
                if (usuario != null) {
                    // Actualizar los datos del usuario
                    usuario.setNombre(nuevoNombre);
                    usuario.setEmail(nuevoEmail);
                    usuario.setTelefono(nuevoTelefono);
                    usuario.setApellidos(nuevoApellido);
                    
                    try {
                        usuario.setRol(Usuario.Rol.valueOf(nuevoRol.toLowerCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Rol inválido: " + nuevoRol);
                        return false;
                    }

                    // Guardar los cambios
                    session.update(usuario);
                    transaction.commit();
                    return true;
                } else {
                    System.out.println("Usuario no encontrado.");
                    return false;
                }
            } else {
                System.out.println("El tipo de userId no es válido.");
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
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Aquí actualizas el usuario con los nuevos datos
            session.update(usuario);
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
}