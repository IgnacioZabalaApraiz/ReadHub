package controlador;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import vista.Login;
import vista.MainPanel;
import vista.PanelInformes;
import vista.Registro;
import vista.AdminPanel;
import vista.AdminSelection;
import vista.BookManagement;
import modeloHibernate.LibrosCRUD;
import modeloHibernate.UsuariosCRUD;
import modeloHibernate.Libro;
import modeloHibernate.PrestamoCRUD;
import modeloHibernate.Usuario;
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
    private UsuariosCRUD usuariosCRUD;
    private SessionFactory sessionFactory;
    private Session session;
    private LibrosCRUD librosCRUD;
    private LibroService libroService;
    private Usuario usuarioConectado;
    private PrestamoCRUD prestamosCRUD;
    private AdminPanel adminPanel;
    private AdminSelection adminSelection;
    private PanelInformes panelInformes;

    public Controlador() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.getCurrentSession(); // Usamos getCurrentSession()
            session.beginTransaction(); // Iniciamos la transacción aquí
            librosCRUD = new LibrosCRUD(session);
            usuariosCRUD = new UsuariosCRUD(session);
            prestamosCRUD = new PrestamoCRUD(session);
            libroService = new LibroServiceImpl();
            adminSelection = new AdminSelection();
            panelInformes = new PanelInformes();
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
        adminPanel = new AdminPanel();
        adminSelection = new AdminSelection();
        panelInformes = new PanelInformes();
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(mainPanel, "main");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registroPanel, "registro");
        cardPanel.add(bookManagementPanel, "bookManagement");
        cardPanel.add(adminPanel, "adminPanel");
        cardPanel.add(adminSelection, "adminSelection");
        cardPanel.add(panelInformes, "panelInformes");

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
    	adminSelection.getInformesBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("panelInformes");
			}
		});
	
        
        loginPanel.getIniciarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = loginPanel.getTxtUsuario().getText();
                String contrasena = new String(loginPanel.getTxtPassword().getPassword());

                usuarioConectado = usuariosCRUD.iniciarSesion(usuario, contrasena);

                if (usuarioConectado != null) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Inicio de sesión exitoso.",
                            "Login exitoso",
                            JOptionPane.INFORMATION_MESSAGE);

                    if (usuarioConectado.getRol() == Usuario.Rol.administrador) {
                        mostrarPanel("adminSelection");
                    } else {
                        bookManagementPanel.setUsuarioConectado(usuarioConectado);
                        mostrarPanel("bookManagement");
                    }
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
                int dni = Integer.parseInt(registroPanel.getTxtDni().getText());

                if (usuariosCRUD.registrarUsuario(nombre, apellidos, contrasena, email, dni, telefono)) {
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
            prestamosCRUD.prestarLibro(libro.getIdLibro(), usuarioConectado.getIdUsuario(), new Date());
            showStyledMessage("Has reservado el libro: " + libro.getTitulo(), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            libro.setDisponibilidad(true);
            libroService.updateLibroDisponibilidad(libro);
            prestamosCRUD.devolverLibro(libro.getIdLibro(), usuarioConectado.getIdUsuario(), new Date());
            showStyledMessage("Has devuelto el libro: " + libro.getTitulo(), "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
        bookManagementPanel.updateView();
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
