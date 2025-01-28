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

    // Constructor
    public AdminSelection() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JPanel panel_10 = new JPanel();
        GridBagConstraints gbc_panel_10 = new GridBagConstraints();
        gbc_panel_10.insets = new Insets(0, 0, 5, 5);
        gbc_panel_10.fill = GridBagConstraints.BOTH;
        gbc_panel_10.gridx = 0;
        gbc_panel_10.gridy = 0;
        add(panel_10, gbc_panel_10);

        // Configuración del botón informesBt
        informesBt = new JButton("INFORMES");
        GridBagConstraints gbc_informesBt = new GridBagConstraints();
        gbc_informesBt.insets = new Insets(0, 0, 5, 5);
        gbc_informesBt.gridx = 1;
        gbc_informesBt.gridy = 1;
        add(informesBt, gbc_informesBt);

        // Configuración del botón usermodifyBt
        usermodifyBt = new JButton("PERMISOS USUARIOS");
        GridBagConstraints gbc_usermodifyBt = new GridBagConstraints();
        gbc_usermodifyBt.insets = new Insets(0, 0, 5, 5);
        gbc_usermodifyBt.gridx = 1;
        gbc_usermodifyBt.gridy = 2;
        add(usermodifyBt, gbc_usermodifyBt);

        // Configuración del botón bookmodifyBt
        bookmodifyBt = new JButton("Modificar Libros");
        GridBagConstraints gbc_bookmodifyBt = new GridBagConstraints();
        gbc_bookmodifyBt.insets = new Insets(0, 0, 5, 5);
        gbc_bookmodifyBt.gridx = 1;
        gbc_bookmodifyBt.gridy = 3;
        add(bookmodifyBt, gbc_bookmodifyBt);
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
        }

        // Método para cambiar de panel
        private void mostrarPanel(String panelName) {
            cardLayout.show(panelContainer, panelName);
        }
    }

    // Método principal para probar el panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        CardLayout cardLayout = new CardLayout();
        JPanel panelContainer = new JPanel(cardLayout);

        AdminSelection adminSelection = new AdminSelection();
        JPanel panelInformes = new JPanel();
        panelInformes.add(new JLabel("Panel de Informes"));
        JPanel panelPermisosUsuarios = new JPanel();
        panelPermisosUsuarios.add(new JLabel("Panel de Permisos de Usuarios"));
        JPanel panelModificarLibros = new JPanel();
        panelModificarLibros.add(new JLabel("Panel de Modificar Libros"));

        panelContainer.add(adminSelection, "adminSelection");
        panelContainer.add(panelInformes, "panelInformes");
        panelContainer.add(panelPermisosUsuarios, "panelPermisosUsuarios");
        panelContainer.add(panelModificarLibros, "panelModificarLibros");

        new Controlador(cardLayout, panelContainer, adminSelection);

        frame.add(panelContainer);
        frame.setVisible(true);
    }
}
