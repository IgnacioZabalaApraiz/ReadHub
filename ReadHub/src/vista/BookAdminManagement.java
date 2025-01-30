package vista;

import javax.imageio.ImageIO;
import java.net.URL;
import modeloHibernate.Libro;
import modeloHibernate.Usuario;
import modeloHibernate.LibrosCRUD;
import modeloHibernate.PrestamoCRUD;
import servicio.LibroService;
import servicio.LibroServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.border.AbstractBorder;

import org.hibernate.Session;

public class BookAdminManagement extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel booksPanel;
    private LibroService libroService;
    private JButton backButton, addButton;
    private Session session;
    private ActionListener reserveBookListener;
    private Usuario usuarioConectado;

    public BookAdminManagement(Session session) {
        this(session, null);
    }

    public BookAdminManagement(Session session, Usuario usuarioConectado) {
        this.session = session;
        this.usuarioConectado = usuarioConectado;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 244, 255));

        libroService = new LibroServiceImpl();
        booksPanel = new JPanel(new GridBagLayout());
        booksPanel.setBackground(new Color(255, 244, 255));

        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(new Color(255, 244, 255));
        add(scrollPane, BorderLayout.CENTER);

        displayBooks();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 244, 255));
        
        backButton = createStyledButton("Volver", new Color(175, 166, 223));
        addButton = createStyledButton("Añadir Libro", new Color(135, 126, 203));
        
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("Catálogo de Libros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(95, 88, 191));
        add(titleLabel, BorderLayout.NORTH);
    }

    private void displayBooks() {
        List<Libro> libros = libroService.getAllLibros();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        int row = 0;
        int col = 0;
        for (Libro libro : libros) {
            gbc.gridx = col;
            gbc.gridy = row;
            booksPanel.add(new BookCard(libro), gbc);

            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }

    private class BookCard extends JPanel {
        private Libro libro;
        private JButton reserveButton, editButton, deleteButton;

        public BookCard(Libro libro) {
            this.libro = libro;
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(220, 420));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(215, 205, 239), 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel(libro.getTitulo(), SwingConstants.CENTER);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            
            editButton = createStyledButton("✎ Modificar", new Color(120, 110, 200));
            deleteButton = createStyledButton("✖ Eliminar", new Color(200, 80, 100));
            
            editButton.addActionListener(e -> modifyBook());
            deleteButton.addActionListener(e -> deleteBook());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
            
            add(titleLabel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        private void modifyBook() {
            JOptionPane.showMessageDialog(null, "Modificar datos del libro: " + libro.getTitulo());
        }

        private void deleteBook() {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este libro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                LibrosCRUD librosCRUD = new LibrosCRUD(session);
                librosCRUD.borrarLibro(libro.getIdLibro());
                updateView();
            }
        }
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void updateView() {
        booksPanel.removeAll();
        displayBooks();
        booksPanel.revalidate();
        booksPanel.repaint();
    }
}
