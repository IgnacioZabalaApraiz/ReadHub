package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import servicio.UsuarioServiceImpl;
import modeloHibernate.Usuario;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {
    private JTable table;
    private JPanel titlePanel;
    private DefaultTableModel tableModel;
    private UsuarioServiceImpl usuarioService;
    private JLabel titleLabel;
    private JButton reloadButton;
    private JButton removeButton;
    private JButton grantPermissionButton;
    private JButton deleteButton;
    private JButton editButton;  // Botón para editar
    private JButton backButton;
    
    public AdminPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 244, 255));

        usuarioService = new UsuarioServiceImpl();
        
        // Create title
        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 244, 255));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        titleLabel = new JLabel("Panel de Administración");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(95, 88, 191));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Usuario");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefono");
        tableModel.addColumn("Rol");
        
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(215, 205, 239));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 244, 255));
        scrollPane.getViewport().setBackground(new Color(255, 244, 255));
        add(scrollPane, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 244, 255));
        
        reloadButton = createStyledButton("Recargar Usuarios", new Color(95, 88, 191));
        reloadButton.addActionListener(e -> loadUsers());
        buttonPanel.add(reloadButton);
        
       
        
        
        deleteButton = createStyledButton("Borrar", new Color(200, 50, 50));
        buttonPanel.add(deleteButton);
        
        // Botón de editar
        editButton = createStyledButton("Editar", new Color(100, 150, 255));
        buttonPanel.add(editButton);
        
        backButton = createStyledButton("Volver", new Color(150, 150, 150));
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();
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

    public void loadUsers() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar los usuarios o la lista está vacía.");
        } else {
            tableModel.setRowCount(0); // Clear existing rows
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{
                    usuario.getIdUsuario(),
                    usuario.getNombre() + " " + usuario.getApellidos(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getRol()
                });
            }
        }
    }

    public JButton getReloadButton() {
        return reloadButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getGrantPermissionButton() {
        return grantPermissionButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
