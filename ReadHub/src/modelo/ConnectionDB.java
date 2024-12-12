package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private final String BBDD;
	private final String USER;
	private final String PASSWORD;
	
	public ConnectionDB() {
		this.BBDD = "jdbc:mysql://localhost:3306/biblioteca";
		this.USER = "root";
		this.PASSWORD = "root";
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(BBDD, USER, PASSWORD);
	}

}
