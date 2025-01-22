package controlador;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import vista.Login;
import vista.MainPanel;
import vista.Registro;
import vista.BookManagement;
import modeloHibernate.LibrosCRUD;
import modeloHibernate.UsuariosCRUD;
import modeloHibernate.Libro;
import modeloHibernate.PrestamoCRUD;
import servicio.LibroService;
import servicio.LibroServiceImpl;

public class Controlador {

    private JFrame mainFrame;
    private MainPanel mainPanel;
    private Login loginPanel;
    private Registro registroPanel;
    private BookManagement bookManagementPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private UsuariosCRUD usuarioHibernate;
    private SessionFactory sessionFactory;
    private Session session;
    private LibrosCRUD librosCRUD;
    private LibroService libroService;
    
    public Controlador() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            librosCRUD = new LibrosCRUD(session);
            usuarioHibernate = new UsuariosCRUD(session);
            libroService = new LibroServiceImpl();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        mainFrame = new JFrame("ReadHub");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);
        mainFrame.setLocationRelativeTo(null);
        ImageIcon icono = new ImageIcon("imagenes/bibliotecalogo.png");
        mainFrame.setIconImage(icono.getImage());

        mainPanel = new MainPanel();
        loginPanel = new Login();
        registroPanel = new Registro();
        bookManagementPanel = new BookManagement(session);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(mainPanel, "main");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registroPanel, "registro");
        cardPanel.add(bookManagementPanel, "bookManagement");

        mainFrame.setContentPane(cardPanel);
        cardLayout.show(cardPanel, "main");
    }

    private void configurarEventos() {
        mainPanel.getIniciarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("login");
            }
        });

        mainPanel.getRegistrarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("registro");
            }
        });

        loginPanel.getVolverBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("main");
            }
        });

        loginPanel.getRegistrarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("registro");
            }
        });

        registroPanel.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("main");
            }
        });
        
        bookManagementPanel.getBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("main");
            }
        });

        loginPanel.getIniciarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = loginPanel.getTxtUsuario().getText();
                String contrasena = new String(loginPanel.getTxtPassword().getPassword());

                if (usuarioHibernate.iniciarSesion(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Inicio de sesión exitoso.",
                            "Login exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarPanel("bookManagement");
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Credenciales incorrectas. Intente de nuevo.",
                            "Error de login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registroPanel.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = registroPanel.getTxtNombre().getText();
                String apellidos = registroPanel.getTxtApellidos().getText();
                String email = registroPanel.getTxtEmail().getText();
                int telefono = Integer.parseInt(registroPanel.getTxtTelefono().getText());
                String contrasena = new String(registroPanel.getTxtPassword().getPassword());
                int dni =  Integer.parseInt(registroPanel.getTxtDni().getText());

                if (usuarioHibernate.registrarUsuario(nombre, apellidos, contrasena, email, dni, telefono)) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Usuario registrado exitosamente.",
                            "Registro exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarPanel("login");
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Error al registrar usuario. Intente de nuevo.",
                            "Error de registro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bookManagementPanel.setReserveBookListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro libro = (Libro) e.getSource();
                reserveBook(libro);
            }
        });
    }

    private void mostrarPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    private void reserveBook(Libro libro) {
        if (libro.getDisponibilidad()) {
            libro.setDisponibilidad(false);
            libroService.updateLibroDisponibilidad(libro);
            var prestamos = new PrestamoCRUD(session);
            prestamos.prestarLibro(null, null, null, null);
            showStyledMessage("Has reservado el libro: " + libro.getTitulo(), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            bookManagementPanel.updateView();
        } else {
            showStyledMessage("El libro no está disponible para reserva.", "Error de Reserva", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStyledMessage(String message, String title, int messageType) {
        UIManager.put("OptionPane.background", new Color(255, 244, 255));
        UIManager.put("Panel.background", new Color(255, 244, 255));
        UIManager.put("OptionPane.messageForeground", new Color(95, 88, 191));
        JOptionPane.showMessageDialog(mainFrame, message, title, messageType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controlador controlador = new Controlador();
                    controlador.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

