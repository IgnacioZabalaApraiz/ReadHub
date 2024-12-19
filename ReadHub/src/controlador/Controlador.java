package controlador;

import java.awt.EventQueue;
<<<<<<< HEAD
import java.sql.*;
import java.time.LocalDate;

import modelo.ConnectBd;
import modelo.CrudOperations;

import java.awt.EventQueue;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import vista.Login;
>>>>>>> branch 'newdevelop' of https://github.com/IgnacioZabalaApraiz/ReadHub.git
import vista.MainPanel;
import vista.Registro;

public class Controlador {
	
	public static void main(String[] args) {	
        Connection connection = ConnectBd.getConnection();
        CrudOperations.insertarLibro(connection, "prueba", "prueba", "prueba", LocalDate.of(2010,2,2),1);
        CrudOperations.listarLibros(connection);
        CrudOperations.eliminarLibro(connection, "prueba");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel ventanaPrincipal = new MainPanel();
					ventanaPrincipal.setVisible(true);
					JPanel mainPanel = ventanaPrincipal.getMainPanel();
					JPanel loginPanel = new Login();
					
					ventanaPrincipal.getBtnIniciarSesion().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mainPanel.setVisible(false);
							loginPanel.setVisible(true);
							mainPanel.add(loginPanel);
							mainPanel.revalidate();
							mainPanel.repaint();
						}
					});
					ventanaPrincipal.getBtnRegistrar().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							mainPanel.removeAll();
							mainPanel.add(new Registro());
							mainPanel.revalidate();
							mainPanel.repaint();
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
