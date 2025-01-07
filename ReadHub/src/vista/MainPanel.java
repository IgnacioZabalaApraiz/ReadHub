package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {

    private JLabel lblTitle;
    private JButton iniciarBt;
    private JButton registrarBt;

    public MainPanel() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 244, 255)); // #fff4ff

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        lblTitle = new JLabel("ReadHub");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(new Color(95, 88, 191)); // #5f58bf
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        iniciarBt = new JButton("Iniciar Sesión");
        iniciarBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        iniciarBt.setForeground(Color.WHITE);
        iniciarBt.setBackground(new Color(95, 88, 191)); // #5f58bf
        iniciarBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        iniciarBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(iniciarBt, gbc);

        registrarBt = new JButton("Registrarse");
        registrarBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registrarBt.setForeground(Color.WHITE);
        registrarBt.setBackground(new Color(175, 166, 223)); // #afa6df
        registrarBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        registrarBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(registrarBt, gbc);
    }

    public JButton getIniciarBt() {
        return iniciarBt;
    }

    public JButton getRegistrarBt() {
        return registrarBt;
    }
}