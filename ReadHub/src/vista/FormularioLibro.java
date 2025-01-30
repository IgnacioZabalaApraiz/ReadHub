package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import modeloHibernate.Libro;
import servicio.LibroService;

public class FormularioLibro extends JFrame {
    private JTextField txtTitulo, txtAutor, txtGenero, txtUrlImagen;
    private JSpinner spinnerFechaPublicacion;
    private JCheckBox chkDisponibilidad;
    private JButton btnGuardar, btnCancelar;
    private LibroService libroService;

    // Definición de colores
    private static final Color COLOR_1 = new Color(0x5f58bf);
    private static final Color COLOR_2 = new Color(0x877fcf);
    private static final Color COLOR_3 = new Color(0xafa6df);
    private static final Color COLOR_4 = new Color(0xd7cdef);
    private static final Color COLOR_5 = new Color(0xfff4ff);

    public FormularioLibro(LibroService libroService) {
        this.libroService = libroService;
        setTitle("Registro de Libro");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal con color de fondo y borde redondeado
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(COLOR_5);
        mainPanel.setBorder(new CompoundBorder(
            new EmptyBorder(20, 20, 20, 20),
            new LineBorder(COLOR_2, 2, true)
        ));
        setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        // Título del formulario
        JLabel titleLabel = new JLabel("Nuevo Libro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(COLOR_1);
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Crear y añadir componentes
        addLabelAndField(mainPanel, "Título:", txtTitulo = createStyledTextField(), gbc, 1);
        addLabelAndField(mainPanel, "Autor:", txtAutor = createStyledTextField(), gbc, 2);
        addLabelAndField(mainPanel, "Género:", txtGenero = createStyledTextField(), gbc, 3);

        addLabelAndField(mainPanel, "Fecha de Publicación:", createDateSpinner(), gbc, 4);

        addLabelAndField(mainPanel, "Disponibilidad:", createStyledCheckBox(), gbc, 5);
        addLabelAndField(mainPanel, "URL Imagen:", txtUrlImagen = createStyledTextField(), gbc, 6);

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        btnGuardar = createStyledButton("Guardar", COLOR_1, COLOR_5);
        btnCancelar = createStyledButton("Cancelar", COLOR_3, COLOR_1);
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        mainPanel.add(buttonPanel, gbc);

        // Acciones de los botones
        btnGuardar.addActionListener(e -> guardarLibro());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(COLOR_4);
        textField.setForeground(COLOR_1);
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_2),
            new EmptyBorder(5, 7, 5, 7)
        ));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JComponent createDateSpinner() {
        spinnerFechaPublicacion = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaPublicacion, "dd/MM/yyyy");
        spinnerFechaPublicacion.setEditor(dateEditor);
        spinnerFechaPublicacion.setFont(new Font("Arial", Font.PLAIN, 14));
        return spinnerFechaPublicacion;
    }

    private JCheckBox createStyledCheckBox() {
        chkDisponibilidad = new JCheckBox();
        chkDisponibilidad.setBackground(COLOR_5);
        chkDisponibilidad.setForeground(COLOR_1);
        return chkDisponibilidad;
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(new CompoundBorder(
            new LineBorder(fgColor),
            new EmptyBorder(8, 15, 8, 15)
        ));
        return button;
    }

    private void addLabelAndField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc, int gridy) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(COLOR_1);
        
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }

    private void guardarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        Date fechaPublicacion = (Date) spinnerFechaPublicacion.getValue();
        boolean disponibilidad = chkDisponibilidad.isSelected();
        String urlImagen = txtUrlImagen.getText();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libro = new Libro(titulo, autor, genero, fechaPublicacion, disponibilidad, urlImagen);
        libroService.saveLibro(libro);

        JOptionPane.showMessageDialog(this, "Libro guardado:\n" + libro.getTitulo(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new FormularioLibro(null).setVisible(true);
        });
    }
}