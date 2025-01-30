package servicio;

import modeloHibernate.Usuario;
import java.util.List;

public interface UsuarioService { 
	//miodeloHibernate.Usuario
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorId(Long id);
    void guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Long id);
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(int id);
    boolean registrarUsuario(String nombre, String apellidos, String contrasena, String email, int dni, int telefono);
   void editarUsuario(Object userId, String nuevoNombre,String nuevoApellido, String nuevoEmail, int nuevoTelefono, String nuevoRol) ;
}