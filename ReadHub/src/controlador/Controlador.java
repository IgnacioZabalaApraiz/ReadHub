package controlador;
<<<<<<< HEAD
import java.awt.EventQueue;
import java.sql.*;
=======
>>>>>>> refs/remotes/origin/newdevelop

import java.awt.EventQueue;
import vista.MainPanel;

public class Controlador {
	
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
