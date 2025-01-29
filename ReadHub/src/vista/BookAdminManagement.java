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
    private JButton backButton;
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
        buttonPanel.add(backButton);
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

    public void setReserveBookListener(ActionListener listener) {
        this.reserveBookListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
                updateView();
            }
        };
    }

    public void setUsuarioConectado(Usuario usuarioConectado) {
        this.usuarioConectado = usuarioConectado;
        updateView();
    }

    private boolean isBookBorrowedByUser(Libro libro) {
        if (usuarioConectado == null || libro == null) {
            return false;
        }
        PrestamoCRUD prestamoCRUD = new PrestamoCRUD(session);
        return prestamoCRUD.isBookBorrowedByUser(libro.getIdLibro(), usuarioConectado.getIdUsuario());
    }

    private class BookCard extends JPanel {
        private Libro libro;
        private JButton reserveButton;
        private JButton editButton;
        private JButton deleteButton;

        public BookCard(Libro libro) {
            this.libro = libro;
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(220, 420));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(215, 205, 239), 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            setBackground(Color.WHITE);

            JLabel coverLabel = new JLabel();
            coverLabel.setHorizontalAlignment(JLabel.CENTER);
            coverLabel.setVerticalAlignment(JLabel.CENTER);
            coverLabel.setPreferredSize(new Dimension(180, 240));

            coverLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(4, 4, 4, 4)
            ));

            try {
                URL url = new URL(libro.getUrlImagen());
                BufferedImage originalImage = ImageIO.read(url);
                if (originalImage != null) {
                    BufferedImage scaledImage = new BufferedImage(180, 240, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = scaledImage.createGraphics();

                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    double originalWidth = originalImage.getWidth();
                    double originalHeight = originalImage.getHeight();
                    double scaleFactor = Math.min(180.0 / originalWidth, 240.0 / originalHeight);

                    int scaledWidth = (int) (originalWidth * scaleFactor);
                    int scaledHeight = (int) (originalHeight * scaleFactor);

                    int x = (180 - scaledWidth) / 2;
                    int y = (240 - scaledHeight) / 2;

                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, 180, 240);

                    g2d.drawImage(originalImage, x, y, scaledWidth, scaledHeight, null);
                    g2d.dispose();

                    coverLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    coverLabel.setText("Imagen no disponible");
                }
            } catch (Exception e) {
                coverLabel.setText("Imagen no disponible");
                e.printStackTrace();
            }

            JPanel coverWrapper = new JPanel(new BorderLayout());
            coverWrapper.setBackground(Color.WHITE);
            coverWrapper.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(4, 4, 8, 4),
                    new ShadowBorder()
            ));
            coverWrapper.add(coverLabel);

            add(coverWrapper, BorderLayout.CENTER);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(12, 8, 8, 8));

            JLabel titleLabel = new JLabel(libro.getTitulo());
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel authorLabel = new JLabel(libro.getAutor());
            authorLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel genreLabel = new JLabel("Género: " + libro.getGenero());
            genreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel publishDateLabel = new JLabel("Publicado: " + libro.getFechaPublicacion());
            publishDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            publishDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel availabilityLabel = new JLabel("Disponibles: " + libro.getDisponibilidad());
            availabilityLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            availabilityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            reserveButton = createStyledButton("Reservar", new Color(95, 88, 191));
            reserveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            reserveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reserveBook();
                }
            });

            editButton = createStyledButton("Modificar", new Color(255, 193, 7));
            editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modifyBook();
                }
            });

            deleteButton = createStyledButton("Eliminar", new Color(220, 53, 69));
            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteBook();
                }
            });

            infoPanel.add(titleLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(authorLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(genreLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(publishDateLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(availabilityLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            infoPanel.add(reserveButton);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(editButton);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            infoPanel.add(deleteButton);

            add(infoPanel, BorderLayout.SOUTH);

            updateButtonState();
        }

        private void updateButtonState() {
            boolean isBookBorrowedByUser = isBookBorrowedByUser(libro);
            if (libro.getDisponibilidad()) {
                reserveButton.setText("Reservar");
                reserveButton.setBackground(new Color(95, 88, 191));
                reserveButton.setEnabled(usuarioConectado != null);
            } else if (isBookBorrowedByUser) {
                reserveButton.setText("Devolver");
                reserveButton.setBackground(new Color(0, 150, 136));
                reserveButton.setEnabled(true);
            } else {
                reserveButton.setText("No Disponible");
                reserveButton.setBackground(Color.GRAY);
                reserveButton.setEnabled(false);
            }
        }

        private void reserveBook() {
            if (reserveBookListener != null) {
                reserveBookListener.actionPerformed(new ActionEvent(libro, ActionEvent.ACTION_PERFORMED, "reserveBook"));
            }
        }

        private void modifyBook() {
            // Lógica para modificar los datos del libro
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

    private class ShadowBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gradient = new GradientPaint(
                    0, height - 4, new Color(0, 0, 0, 50),
                    0, height, new Color(0, 0, 0, 0)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(x + 4, height - 4, width - 8, 4);

            gradient = new GradientPaint(
                    width - 4, 0, new Color(0, 0, 0, 50),
                    width, 0, new Color(0, 0, 0, 0)
            );
            g2d.setPaint(gradient);
            g2d.fillRect(width - 4, y + 4, 4, height - 8);

            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 4, 4);
        }
    }

    public void updateView() {
        booksPanel.removeAll();
        displayBooks();
        booksPanel.revalidate();
        booksPanel.repaint();
    }
}
