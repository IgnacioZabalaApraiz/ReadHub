package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminSelection extends JPanel {
    // Atributos de instancia
<<<<<<< HEAD
    private JButton informesBt;
    private JButton usermodifyBt;
    private JButton bookmodifyBt;
    private JButton backButton;
=======
	private JLabel lblTitle;
    private JButton informesBt, usermodifyBt, bookmodifyBt, closeBt;
>>>>>>> dd19adaf63acf17e060283673f18244b59e38fd6

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
<<<<<<< HEAD
        mainPanel.add(bookmodifyBt, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Panel para el botón de volver
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 244, 255));
        backButton = createStyledButton("Volver al Login", new Color(175, 166, 223));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        return createStyledButton(text, new Color(175, 166, 223));
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
=======
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
>>>>>>> dd19adaf63acf17e060283673f18244b59e38fd6
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

<<<<<<< HEAD
    public JButton getBackButton() {
        return backButton;
    }

    // Clase Controlador
    public static class Controlador {
        private CardLayout cardLayout;
        private JPanel panelContainer;

        public Controlador(CardLayout cardLayout, JPanel panelContainer, AdminSelection adminSelection) {
            this.cardLayout = cardLayout;
            this.panelContainer = panelContainer;

            // Configuración de eventos para los botones
            adminSelection.getInformesBt().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarPanel("panelInformes");
                }
            });

            adminSelection.getUsermodifyBt().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarPanel("panelPermisosUsuarios");
                }
            });

            adminSelection.getBookmodifyBt().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarPanel("panelModificarLibros");
                }
            });

            // No es necesario configurar el ActionListener para el botón de volver aquí
        }

        // Método para cambiar de panel
        private void mostrarPanel(String panelName) {
            cardLayout.show(panelContainer, panelName);
        }
    }

    // Método principal para probar el panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            CardLayout cardLayout = new CardLayout();
            JPanel panelContainer = new JPanel(cardLayout);
            panelContainer.setBackground(new Color(255, 244, 255));

            AdminSelection adminSelection = new AdminSelection();
            JPanel panelInformes = createStyledPanel("Panel de Informes");
            JPanel panelPermisosUsuarios = createStyledPanel("Panel de Permisos de Usuarios");
            JPanel panelModificarLibros = createStyledPanel("Panel de Modificar Libros");

            panelContainer.add(adminSelection, "adminSelection");
            panelContainer.add(panelInformes, "panelInformes");
            panelContainer.add(panelPermisosUsuarios, "panelPermisosUsuarios");
            panelContainer.add(panelModificarLibros, "panelModificarLibros");

            new Controlador(cardLayout, panelContainer, adminSelection);

            frame.add(panelContainer);
            frame.setVisible(true);
        });
    }

    private static JPanel createStyledPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 244, 255));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(95, 88, 191));
        panel.add(titleLabel, BorderLayout.NORTH);
        return panel;
    }
}
=======
    public JButton getCloseBt() {
		return closeBt;
	}

}
>>>>>>> dd19adaf63acf17e060283673f18244b59e38fd6
