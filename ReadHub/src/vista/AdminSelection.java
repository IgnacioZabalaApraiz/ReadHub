package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSelection extends JPanel {
    // Atributos de instancia
    private JButton informesBt;
    private JButton usermodifyBt;
    private JButton bookmodifyBt;
    private JButton logoutButton;

    // Constructor
    public AdminSelection() {
        setBackground(new Color(255, 244, 255)); // Fondo similar al del panel de libros
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 244, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Panel de Administración", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(95, 88, 191));
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Configuración de los botones
        informesBt = createStyledButton("INFORMES");
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(informesBt, gbc);

        usermodifyBt = createStyledButton("PERMISOS USUARIOS");
        gbc.gridy = 2;
        mainPanel.add(usermodifyBt, gbc);

        bookmodifyBt = createStyledButton("Modificar Libros");
        gbc.gridy = 3;
        mainPanel.add(bookmodifyBt, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Panel para el botón de cerrar sesión
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 244, 255));
        logoutButton = createStyledButton("Cerrar sesión", new Color(175, 166, 223));
        buttonPanel.add(logoutButton);
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

    public JButton getLogoutButton() {
        return logoutButton;
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

            adminSelection.getLogoutButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes agregar la lógica para cerrar sesión
                    System.out.println("Cerrando sesión...");
                }
            });
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