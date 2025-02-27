package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Registro extends JPanel {

    private JLabel lblTitle;
    private JTextField txtNombre, txtApellidos, txtEmail, txtTelefono, txtDni;
    private JPasswordField txtPassword;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public Registro() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 244, 255)); // #fff4ff

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        lblTitle = new JLabel("Registro en ReadHub");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(95, 88, 191)); // #5f58bf
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        addField("Nombre:", txtNombre = new JTextField(20), gbc, 1);
        addField("Apellidos:", txtApellidos = new JTextField(20), gbc, 2);
        addField("Email:", txtEmail = new JTextField(20), gbc, 3);
        addField("Teléfono:", txtTelefono = new JTextField(20), gbc, 4);
        addField("DNI:", txtDni = new JTextField(20), gbc, 5);
        addField("Contraseña:", txtPassword = new JPasswordField(20), gbc, 6);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBackground(new Color(95, 88, 191)); // #5f58bf
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRegistrar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setBackground(new Color(215, 205, 239)); // #d7cdef
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(btnVolver, gbc);
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtApellidos() {
        return txtApellidos;
    }

    public void setTxtApellidos(JTextField txtApellidos) {
        this.txtApellidos = txtApellidos;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public void setTxtDni(JTextField txtDni) {
        this.txtDni = txtDni;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    private void addField(String labelText, JTextField textField, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
