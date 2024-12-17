package modelo;

import java.sql.Connection;
import java.sql.SQLException;

public class OperationsDB {
	
	
	public OperationsDB() {
		
	}
	
	private String[][] ConsultBooks(Connection conn) {
		var consult = "SELECT * FROM libros";
		try(var preparedStatement = conn.prepareStatement(consult)){
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
