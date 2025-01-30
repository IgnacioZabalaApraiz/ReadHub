package controlador;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import vista.BookAdminManagement;
import vista.BookManagement;
import modeloHibernate.LibrosCRUD;
import modeloHibernate.UsuariosCRUD;
import modeloHibernate.Libro;
import modeloHibernate.PrestamoCRUD;
import modeloHibernate.Usuario;
import servicio.LibroService;
import servicio.LibroServiceImpl;
import servicio.HibernateUtil;

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
    private BookAdminManagement bookAdminManagement;

    public Controlador() {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction(); 
            librosCRUD = new LibrosCRUD(session);
            usuariosCRUD = new UsuariosCRUD();
            prestamosCRUD = new PrestamoCRUD(session);
            libroService = new LibroServiceImpl();
            adminSelection = new AdminSelection();
            panelInformes = new PanelInformes();
            bookAdminManagement = new BookAdminManagement(session);
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
        mainFrame.setSize(900, 600);
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
        bookAdminManagement = new BookAdminManagement(session);
        
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
        cardPanel.add(bookAdminManagement, "bookAdminManagement");
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
        
		bookAdminManagement.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("adminSelection");
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
	
		adminSelection.getUsermodifyBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("adminPanel");
			}
		});
		
		adminSelection.getBookmodifyBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanel("bookAdminManagement");
			}
		});
		
		adminSelection.getCloseBt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("main");
            }
        });
		adminPanel.getDeleteButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = adminPanel.getTable().getSelectedRow();		        
		        if (selectedRow != -1) {
		          var userId =  adminPanel.getTable().getModel().getValueAt(selectedRow, 0);		            
		            boolean success = usuariosCRUD.eliminarUsuario(userId);		          
		            if (success) {
		                JOptionPane.showMessageDialog(mainFrame, "Usuario eliminado exitosamente.");
		                adminPanel.loadUsers();
		            } else {
		                JOptionPane.showMessageDialog(mainFrame, "Error al eliminar el usuario.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(mainFrame, "Por favor, selecciona un usuario para eliminar.");
		        }
		    }
		});
		adminPanel.getEditButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la fila seleccionada
		        int selectedRow = adminPanel.getTable().getSelectedRow();
		        
		        if (selectedRow != -1) {
		            // Obtener el ID del usuario seleccionado
		            Object userId = adminPanel.getTable().getModel().getValueAt(selectedRow, 0); // ID como Object
		            
		            // Pedir los nuevos datos para editar
		            String nuevoNombre = JOptionPane.showInputDialog(adminPanel, "Nuevo nombre:");
		            String nuevoEmail = JOptionPane.showInputDialog(adminPanel, "Nuevo email:");
		            String telefonoStr = JOptionPane.showInputDialog(adminPanel, "Nuevo teléfono:");
		            String nuevoRol = JOptionPane.showInputDialog(adminPanel, "Nuevo rol (usuario/administrador):");
		            String nuevoApellido = JOptionPane.showInputDialog(adminPanel, "Nuevo apellido:");
		            
		            int nuevoTelefono = 0;
		            try {
		                nuevoTelefono = Integer.parseInt(telefonoStr); // Convertir teléfono a int
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(adminPanel, "El teléfono debe ser un número válido.");
		                return;
		            }
		            
		            // Llamar al método de edición de usuario
		            boolean success = usuariosCRUD.editarUsuario(userId, nuevoNombre,nuevoApellido, nuevoEmail, nuevoTelefono, nuevoRol);

		            if (success) {
		                JOptionPane.showMessageDialog(adminPanel, "Usuario actualizado exitosamente.");
		                adminPanel.loadUsers(); // Recargar los usuarios en la tabla
		            } else {
		                JOptionPane.showMessageDialog(adminPanel, "Error al actualizar el usuario.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(adminPanel, "Por favor, selecciona un usuario para editar.");
		        }
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
        
        panelInformes.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("adminSelection");
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

    private void closeResources() {
        if (session != null && session.isOpen()) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            HibernateUtil.closeSession();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controlador controlador = new Controlador();
                    controlador.mainFrame.setVisible(true);
                    controlador.mainFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            controlador.closeResources();
                            HibernateUtil.closeSessionFactory();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
