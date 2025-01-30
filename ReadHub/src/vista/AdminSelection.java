package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminSelection extends JPanel {
    // Atributos de instancia
	private JLabel lblTitle;
    private JButton informesBt, usermodifyBt, bookmodifyBt, closeBt;

    // Constructor
    public AdminSelection() {
    	setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 244, 255)); // #fff4ff
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblTitle = new JLabel("Panel de Administración");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(95, 88, 191)); // #5f58bf
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // Configuración del botón informesBt
        informesBt = new JButton("Informes");
        informesBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        informesBt.setForeground(Color.WHITE);
        informesBt.setBackground(new Color(95, 88, 191)); // #5f58bf
        informesBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        informesBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(informesBt, gbc);
        
        // Configuración del botón usermodifyBt
        usermodifyBt = new JButton("Permisos usuarios");
        usermodifyBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usermodifyBt.setForeground(Color.WHITE);
        usermodifyBt.setBackground(new Color(135, 127, 207)); // #877fcf
        usermodifyBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        usermodifyBt.setFocusPainted(false);
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(usermodifyBt, gbc);
		
        // Configuración del botón bookmodifyBt
        bookmodifyBt = new JButton("Modificar Libros");
        bookmodifyBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookmodifyBt.setForeground(Color.WHITE);
        bookmodifyBt.setBackground(new Color(175, 166, 223)); // #afa6df
        bookmodifyBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bookmodifyBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(bookmodifyBt, gbc);
        
        closeBt = new JButton("Cerrar sesión");
        closeBt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closeBt.setForeground(Color.BLACK);
        closeBt.setBackground(Color.WHITE);
        closeBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		closeBt.setFocusPainted(false);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		add(closeBt, gbc);
    }

    // Getters
    public JButton getInformesBt() {
        return informesBt;
    }

    public JButton getUsermodifyBt() {
        return usermodifyBt;
    }

    public JButton getBookmodifyBt() {
        return bookmodifyBt;
    }

    public JButton getCloseBt() {
		return closeBt;
	}

}