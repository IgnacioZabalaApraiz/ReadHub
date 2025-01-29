package servicio;

import modeloHibernate.Usuario;
import modeloHibernate.UsuariosCRUD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuariosCRUD usuariosCRUD;

    public UsuarioServiceImpl() {
        Session session = HibernateUtil.getSession();
        usuariosCRUD = new UsuariosCRUD();
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuariosCRUD.obtenerTodosUsuarios();
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return usuariosCRUD.obtenerUsuarioPorId(id);
    }

    @Override
    public boolean registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono) {
        return usuariosCRUD.registrarUsuario(nombre, apellidos, contrasena, email, dni, telefono);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuariosCRUD.obtenerTodosUsuarios();
    }
    //clases

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuariosCRUD.obtenerUsuarioPorId(id.intValue());
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuariosCRUD.registrarUsuario(usuario.getNombre(), usuario.getApellidos(), usuario.getContrasena(), usuario.getEmail(), usuario.getDni(), usuario.getTelefono());
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuariosCRUD.actualizarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuariosCRUD.eliminarUsuario(id.intValue());
    }

    public void closeSession() {
        HibernateUtil.closeSession();
    }
}

