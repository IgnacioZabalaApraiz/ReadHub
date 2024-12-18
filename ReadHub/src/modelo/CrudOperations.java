package modelo;

import java.sql.*;

public class CrudOperations {
	
	public static void insertarProducto(Connection con, String titulo, String autor,String genero, String fecha_publicacion) {
		String query="INSERT INTO libros(titulo,autor,genero,fecha_publicacion,disponibilidad) VALUES (?,?,?,?,?);" ;
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, titulo);
			pst.setString(2, autor);
			pst.setString(3, genero);
			pst.setString(4, fecha_publicacion);
			pst.executeUpdate();
			System.out.println("INGRESADO CORRECTAMENTE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void listarProducto(Connection con, String category) {
		String query="SELECT * FROM productos WHERE category = ? ;";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, category);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
		
		} catch (SQLException e) {
		System.out.println("no se encontraron datos");
			e.printStackTrace();
		}
	}
	public static void actualizarProducto(Connection con, int id, int nuevoStock) {
		String query="UPDATE productos SET stock = ? WHERE product_id = ? ;";
		try (PreparedStatement pst = con.prepareStatement(query)){			
			pst.setInt(1, nuevoStock);
			pst.setInt(2, id);
			pst.executeUpdate();
			System.out.println("Producto actualizado");
		} catch (SQLException e) {
			System.out.println("No se realizaron cambios");	
			e.printStackTrace();
		}
		
	}
	public static void eliminarProducto(Connection con, int stock) {
		String query = "DELETE FROM productos WHERE stock = ? ;";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setInt(1, stock);
			pst.executeUpdate();
			System.out.println("Eiminado correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
