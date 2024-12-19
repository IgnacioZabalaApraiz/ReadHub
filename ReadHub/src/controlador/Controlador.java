package controlador;

import java.awt.EventQueue;
import java.sql.*;
import java.time.LocalDate;

import modelo.ConnectBd;
import modelo.CrudOperations;

import java.awt.EventQueue;
import vista.MainPanel;

public class Controlador {
	
	public static void main(String[] args) {	
        Connection connection = ConnectBd.getConnection();
        CrudOperations.insertarLibro(connection, "prueba", "prueba", "prueba", LocalDate.of(2010,2,2),1);
        CrudOperations.listarLibros(connection);
        CrudOperations.eliminarLibro(connection, "prueba");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
