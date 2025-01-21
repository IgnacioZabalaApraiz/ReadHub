package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import vista.Login;
import vista.MainPanel;
import vista.Registro;
import vista.BookManagement; // Nueva vista para gestionar libros
import modelo.UsuarioModelo; // Clase donde está el método registrarUsuario
import modeloHibernate.LibrosCRUD;
import modeloHibernate.UsuariosCRUD;

public class Controlador {

    private JFrame mainFrame;
    private MainPanel mainPanel;
    private Login loginPanel;
    private Registro registroPanel;
    private BookManagement bookManagementPanel; // Instancia del panel de Book Management
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private UsuariosCRUD usuarioHibernate; // Instancia del modelo para validar usuarios y registrar nuevos
    private SessionFactory sessionFactory;
    private LibrosCRUD librosCRUD;
    
    public Controlador() {
    	try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            librosCRUD = new LibrosCRUD(session);
            usuarioHibernate = new UsuariosCRUD(session);
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
        bookManagementPanel = new BookManagement(); // Instancia el panel de Book Management

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Añadir todos los paneles
        cardPanel.add(mainPanel, "main");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registroPanel, "registro");
        cardPanel.add(bookManagementPanel, "bookManagement"); // Añadir el panel de Book Management

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
        // Evento para iniciar sesión
        loginPanel.getIniciarBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = loginPanel.getTxtUsuario().getText(); // Obtener el email
                String contrasena = new String(loginPanel.getTxtPassword().getPassword()); // Obtener la contraseña

                // Validar las credenciales
                if (usuarioHibernate.iniciarSesion(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Inicio de sesión exitoso.",
                            "Login exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarPanel("bookManagement"); // Mostrar el panel de Book Management
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Credenciales incorrectas. Intente de nuevo.",
                            "Error de login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Evento para registrar usuario en la base de datos
        registroPanel.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = registroPanel.getTxtNombre().getText();
                String apellidos = registroPanel.getTxtApellidos().getText();
                String email = registroPanel.getTxtEmail().getText();
                int telefono = Integer.parseInt(registroPanel.getTxtTelefono().getText());
                String contrasena = new String(registroPanel.getTxtPassword().getPassword());
                int dni =  Integer.parseInt(registroPanel.getTxtDni().getText());

                // Llamar al método de registrarUsuario en el modelo
                if (usuarioHibernate.registrarUsuario(nombre, apellidos, contrasena, email, dni, telefono)) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Usuario registrado exitosamente.",
                            "Registro exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                    mostrarPanel("login"); // Volver al panel de login después del registro
                } else {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Error al registrar usuario. Intente de nuevo.",
                            "Error de registro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void mostrarPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
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
