package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioModelo {
    private ConnectionDB connectionDB;

    public UsuarioModelo() {
        this.connectionDB = new ConnectionDB(); // Inicializa la conexión a la base de datos
    }


    public boolean validarUsuario(String usuario, String contrasena) {
        String sql = "SELECT COUNT(*) AS total FROM usuarios WHERE email = ? AND contrasena = ?";

        try (Connection conn = connectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario); // Sustituye el primer ? con el nombre de usuario
            ps.setString(2, contrasena); // Sustituye el segundo ? con la contraseña

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0; // Si hay al menos un resultado, las credenciales son válidas
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores (puedes usar un logger aquí)
        }

        return false; // Por defecto, las credenciales no son válidas
    }
    public boolean registrarUsuario(String nombre, String apellidos, String email, String telefono, String contrasena, String dni) {
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, telefono, contrasena, dni) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Se establecen los parámetros en la consulta SQL
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, telefono);
            ps.setString(5, contrasena);
            ps.setString(6, dni);

            // Ejecutar la actualización de la base de datos
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Si se insertó al menos una fila, el registro fue exitoso
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return false; // Si ocurrió un error, retorna false
    }
    
}