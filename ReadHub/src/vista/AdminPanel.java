package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import servicio.UsuarioServiceImpl;
import modeloHibernate.Usuario;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private UsuarioServiceImpl usuarioService;

    public AdminPanel() {
        usuarioService = new UsuarioServiceImpl();
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Usuario");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Email");
        
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
        JButton reloadButton = new JButton("Recargar Usuarios");
        reloadButton.addActionListener(e -> loadUsers());
        add(reloadButton, BorderLayout.SOUTH);

        loadUsers();
    }

    public void loadUsers() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar los usuarios o la lista está vacía.");
        } else {
            tableModel.setRowCount(0); // Clear existing rows
            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{
                    usuario.getApellidos(),
                    usuario.getNombre(),
                    usuario.getEmail()
                });
            }
        }
    }
}