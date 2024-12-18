package controlador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import vista.Login;
import vista.MainPanel;
import vista.Registro;

public class Controlador {
	
	public static void main(String[] args) {
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
