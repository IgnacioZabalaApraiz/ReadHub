package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.Login;
import vista.MainPanel;
import vista.Registro;

public class Controlador {
    
    private JFrame mainFrame;
    private MainPanel mainPanel;
    private Login loginPanel;
    private Registro registroPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public Controlador() {
        inicializarComponentes();
        configurarEventos();
    }
    
    private void inicializarComponentes() {
        mainFrame = new JFrame("ReadHub");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        mainPanel = new MainPanel();
        loginPanel = new Login();
        registroPanel = new Registro();
        
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        cardPanel.add(mainPanel, "main");
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registroPanel, "registro");
        
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