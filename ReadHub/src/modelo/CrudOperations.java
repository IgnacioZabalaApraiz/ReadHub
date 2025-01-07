package modelo;

import java.sql.*;
import java.time.LocalDate;

public class CrudOperations {
	
	public static void insertarLibro(Connection con, String titulo, String autor,String genero, LocalDate fecha_publicacion,int disponibilidad) {
		String query="INSERT INTO libro(titulo,autor,genero,fecha_publicacion,disponibilidad) VALUES (?,?,?,?,?);" ;
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, titulo);
			pst.setString(2, autor);
			pst.setString(3, genero);
			pst.setDate(4, Date.valueOf(fecha_publicacion)); 
			pst.setInt(5,disponibilidad);
			pst.executeUpdate();
			System.out.println("INGRESADO CORRECTAMENTE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void listarLibros(Connection con) {
		String query="SELECT * FROM libro ;";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("titulo")+" "+rs.getString("autor")+" "+rs.getString("genero")+" "+rs.getString("fecha_publicacion"));
			}
		
		} catch (SQLException e) {
		System.out.println("no se encontraron datos");
			e.printStackTrace();
		}
	}
	public static void actualizarProducto(Connection con, int id, int nuevoStock) {
		String query="UPDATE libro SET stock = ? WHERE product_id = ? ;";
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
	public static void eliminarLibro(Connection con, String nombre) {
		String query = "DELETE FROM libro WHERE autor = ? ;";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, nombre);
			pst.executeUpdate();
			System.out.println("Eiminado correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void insertarUsuario(Connection con,String dni, String nombre,String apellidos,String email,String telefono,Boolean admin) {
		String query = "INSERT INTO usuario(dni,nombre,apellidos,email,telefono,rol) VALUES (?,?,?,?,?,?);";
		try(PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);
			pst.setString(4, email);
			pst.setString(5, telefono);
			pst.setBoolean(6, admin);
			
			pst.executeUpdate();
			System.out.println("Usuario añadido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
