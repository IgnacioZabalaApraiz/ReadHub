package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends JPanel {

    private JLabel lblTitle;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton iniciarBt;
    private JButton registrarBt;
    private JButton volverBt;

    public Login() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 244, 255)); // #fff4ff

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblTitle = new JLabel("Iniciar Sesión en ReadHub");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(95, 88, 191)); // #5f58bf
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblUsuario, gbc);

        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsuario, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtPassword, gbc);

        iniciarBt = new JButton("Iniciar Sesión");
        iniciarBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        iniciarBt.setForeground(Color.WHITE);
        iniciarBt.setBackground(new Color(95, 88, 191)); // #5f58bf
        iniciarBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        iniciarBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(iniciarBt, gbc);

        registrarBt = new JButton("Registrarse");
        registrarBt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registrarBt.setForeground(Color.WHITE);
        registrarBt.setBackground(new Color(175, 166, 223)); // #afa6df
        registrarBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        registrarBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(registrarBt, gbc);

        volverBt = new JButton("Volver");
        volverBt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        volverBt.setForeground(Color.BLACK);
        volverBt.setBackground(new Color(215, 205, 239)); // #d7cdef
        volverBt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        volverBt.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(volverBt, gbc);
    }

    public JButton getIniciarBt() {
        return iniciarBt;
    }

    public JButton getRegistrarBt() {
        return registrarBt;
    }

    public JButton getVolverBt() {
        return volverBt;
    }
}