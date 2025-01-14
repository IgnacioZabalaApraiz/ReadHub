package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final String BBDD;
    private final String USER;
    private final String PASSWORD;

    public ConnectionDB() {
        this.BBDD = "jdbc:mysql://localhost:3306/biblioteca"; // Cambia el nombre de la base de datos si es necesario
        this.USER = "root"; // Cambia el usuario si es necesario
        this.PASSWORD = "root"; // Cambia la contraseña si es necesario
    }

    // Hacer el método público para que pueda ser usado desde otras clases
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(BBDD, USER, PASSWORD);
    }
}
