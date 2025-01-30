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

public class ModificarLibro extends JFrame {
    private ActionListener onBookModifiedListener;
    private JTextField txtTitulo, txtAutor, txtGenero;
    private JButton btnGuardar, btnCancelar;
    private LibroService libroService;
    private Libro libro;

    // Definición de colores
    private static final Color COLOR_1 = new Color(0x5f58bf);
    private static final Color COLOR_2 = new Color(0x877fcf);
    private static final Color COLOR_3 = new Color(0xafa6df);
    private static final Color COLOR_4 = new Color(0xd7cdef);
    private static final Color COLOR_5 = new Color(0xfff4ff);
    
    public void setOnBookModifiedListener(ActionListener listener) {
        this.onBookModifiedListener = listener;
    }

    public ModificarLibro(LibroService libroService, Libro libro) {
        this.libroService = libroService;
        this.libro = libro;
        setTitle("Modificar Libro");
        setSize(500, 400);
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
        JLabel titleLabel = new JLabel("Modificar Libro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(COLOR_1);
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Crear y añadir componentes
        addLabelAndField(mainPanel, "Título:", txtTitulo = createStyledTextField(), gbc, 1);
        txtTitulo.setText(libro.getTitulo());
        
        addLabelAndField(mainPanel, "Autor:", txtAutor = createStyledTextField(), gbc, 2);
        txtAutor.setText(libro.getAutor());

        addLabelAndField(mainPanel, "Género:", txtGenero = createStyledTextField(), gbc, 3);
        txtGenero.setText(libro.getGenero());

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        btnGuardar = createStyledButton("Guardar", COLOR_1, COLOR_5);
        btnCancelar = createStyledButton("Cancelar", COLOR_3, COLOR_1);
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        mainPanel.add(buttonPanel, gbc);

        // Acciones de los botones
        btnGuardar.addActionListener(e -> modificarLibro());
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

    private void modificarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Modificar el libro en la base de datos
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setGenero(genero);
        libroService.saveLibro(libro);

        JOptionPane.showMessageDialog(this, "Libro modificado:\n" + libro.getTitulo(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Notificar al controlador que se ha modificado un libro
        if (onBookModifiedListener != null) {
            onBookModifiedListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "bookModified"));
        }

        dispose(); // Cierra el formulario después de modificar el libro
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
         
        });
    }
}
