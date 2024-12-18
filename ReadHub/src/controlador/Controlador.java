package controlador;
import java.awt.EventQueue;
import java.sql.*;

import vista.Login;
import vista.MainPanel;

public class Controlador {
	//Este soy Ignacio
	public static void main(String[] args) {
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
