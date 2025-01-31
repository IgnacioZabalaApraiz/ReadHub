package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelInformes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JButton btnLibrosPrestados, btnUsuariosMasLibrosPrestados, btnLibrosPopulares, btnReporteFinanciero,
			btnGenerosLiterarios, btnVolver;

	public PanelInformes() {
		setLayout(new GridBagLayout());
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(new Color(255, 244, 255)); // #fff4ff

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		lblTitle = new JLabel("Informes");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitle.setForeground(new Color(95, 88, 191)); // #5f58bf
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(lblTitle, gbc);

		btnLibrosPrestados = new JButton("Libros prestados");
		btnLibrosPrestados.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLibrosPrestados.setForeground(Color.WHITE);
		btnLibrosPrestados.setBackground(new Color(95, 88, 191)); // #5f58bf
		btnLibrosPrestados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnLibrosPrestados.setFocusPainted(false);
		btnLibrosPrestados.addActionListener(null
				);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(btnLibrosPrestados, gbc);

		btnUsuariosMasLibrosPrestados = new JButton("Usuarios con más libros prestados");
		btnUsuariosMasLibrosPrestados.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnUsuariosMasLibrosPrestados.setForeground(Color.WHITE);
		btnUsuariosMasLibrosPrestados.setBackground(new Color(135, 127, 207)); // #877fcf
		btnUsuariosMasLibrosPrestados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnUsuariosMasLibrosPrestados.setFocusPainted(false);
		btnUsuariosMasLibrosPrestados.addActionListener(null
				);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		add(btnUsuariosMasLibrosPrestados, gbc);

		btnLibrosPopulares = new JButton("Libros más populares");
		btnLibrosPopulares.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLibrosPopulares.setForeground(Color.WHITE);
		btnLibrosPopulares.setBackground(new Color(175, 166, 223)); // #afa6df
		btnLibrosPopulares.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnLibrosPopulares.setFocusPainted(false);
		btnLibrosPopulares.addActionListener(null
				);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		add(btnLibrosPopulares, gbc);

//		btnReporteFinanciero = new JButton("Reporte financiero");
//		btnReporteFinanciero.setFont(new Font("Segoe UI", Font.BOLD, 14));
//		btnReporteFinanciero.setForeground(Color.WHITE);
//		btnReporteFinanciero.setBackground(new Color(215, 205, 239)); // #d7cdef
//		btnReporteFinanciero.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//		btnReporteFinanciero.setFocusPainted(false);
//		btnReporteFinanciero.addActionListener(null
//				);
//		gbc.gridx = 0;
//		gbc.gridy = 4;
//		gbc.gridwidth = 2;
//		add(btnReporteFinanciero, gbc);

		btnGenerosLiterarios = new JButton("Tendencias por género literario");
		btnGenerosLiterarios.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGenerosLiterarios.setForeground(Color.WHITE);
		btnGenerosLiterarios.setBackground(new Color(215, 205, 239)); // #d7cdef
		btnGenerosLiterarios.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnGenerosLiterarios.setFocusPainted(false);
		btnGenerosLiterarios.addActionListener(null
				);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		add(btnGenerosLiterarios, gbc);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnVolver.setFocusPainted(false);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		add(btnVolver, gbc);

	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JButton getBtnLibrosPrestados() {
		return btnLibrosPrestados;
	}

	public JButton getBtnUsuariosMasLibrosPrestados() {
		return btnUsuariosMasLibrosPrestados;
	}

	public JButton getBtnLibrosPopulares() {
		return btnLibrosPopulares;
	}

	public JButton getBtnReporteFinanciero() {
		return btnReporteFinanciero;
	}

	public JButton getBtnGenerosLiterarios() {
		return btnGenerosLiterarios;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

}
