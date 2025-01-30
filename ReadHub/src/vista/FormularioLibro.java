package vista;

import javax.swing.*;
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
    private JButton btnGuardar;
    private LibroService libroService;

    public FormularioLibro(LibroService libroService) {
        this.libroService = libroService;
        setTitle("Registro de Libro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 5, 5));
        
        // Crear componentes
        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Género:"));
        txtGenero = new JTextField();
        add(txtGenero);

        add(new JLabel("Fecha de Publicación:"));
        spinnerFechaPublicacion = new JSpinner(new SpinnerDateModel());
        add(spinnerFechaPublicacion);

        add(new JLabel("Disponibilidad:"));
        chkDisponibilidad = new JCheckBox();
        add(chkDisponibilidad);
        
        add(new JLabel("URL Imagen:"));
        txtUrlImagen = new JTextField();
        add(txtUrlImagen);
        
        btnGuardar = new JButton("Guardar");
        add(btnGuardar);
        
        // Acción del botón guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });
    }
    
    private void guardarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        Date fechaPublicacion = (Date) spinnerFechaPublicacion.getValue();
        boolean disponibilidad = chkDisponibilidad.isSelected();
        String urlImagen = txtUrlImagen.getText();
        
        // Crear objeto Libro
        Libro libro = new Libro(titulo, autor, genero, fechaPublicacion, disponibilidad, urlImagen);
        
        // Guardar libro en la base de datos a través del servicio
        libroService.saveLibro(libro);
        
        // Confirmación
        JOptionPane.showMessageDialog(this, "Libro guardado:\n" + libro.getTitulo());
        dispose();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioLibro(null).setVisible(true));
    }
}
