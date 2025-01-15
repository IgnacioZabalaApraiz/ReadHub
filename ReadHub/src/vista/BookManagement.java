package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagement extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JButton reserveButton;
    private JButton backButton;

    public BookManagement() {
        setLayout(new BorderLayout());
        
        // Create the table model
        String[] columnNames = {"Título", "Autor", "ISBN", "Disponible"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // Create the table
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add some sample data (replace this with actual data from your database)
        addSampleData();
        
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        reserveButton = new JButton("Reservar Libro");
        backButton = new JButton("Volver");
        
        buttonPanel.add(reserveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveBook();
            }
        });
    }
    
    private void addSampleData() {
        tableModel.addRow(new Object[]{"El Quijote", "Miguel de Cervantes", "9788424922580", "Sí"});
        tableModel.addRow(new Object[]{"Cien años de soledad", "Gabriel García Márquez", "9780307474728", "Sí"});
        tableModel.addRow(new Object[]{"1984", "George Orwell", "9780451524935", "No"});
    }
    
    private void reserveBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            String bookTitle = (String) tableModel.getValueAt(selectedRow, 0);
            String availability = (String) tableModel.getValueAt(selectedRow, 3);
            
            if (availability.equals("Sí")) {
                tableModel.setValueAt("No", selectedRow, 3);
                JOptionPane.showMessageDialog(this, "Has reservado el libro: " + bookTitle);
            } else {
                JOptionPane.showMessageDialog(this, "El libro no está disponible para reserva.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un libro para reservar.");
        }
    }
    
    public JButton getBackButton() {
        return backButton;
    }
}

