package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import modelo.CrudOperations;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Registro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField dniIn;
	private Label label;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnNewButton;
	
	

	public Registro() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		Panel panel_3 = new Panel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		
		label = new Label("DNI:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		dniIn = new JTextField();
		GridBagConstraints gbc_dniIn = new GridBagConstraints();
		gbc_dniIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniIn.insets = new Insets(0, 0, 5, 5);
		gbc_dniIn.gridx = 2;
		gbc_dniIn.gridy = 1;
		add(dniIn, gbc_dniIn);
		
		Label label_1 = new Label("Nombre:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		TextField nombreIn = new TextField();
		GridBagConstraints gbc_nombreIn = new GridBagConstraints();
		gbc_nombreIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreIn.insets = new Insets(0, 0, 5, 5);
		gbc_nombreIn.gridx = 2;
		gbc_nombreIn.gridy = 2;
		add(nombreIn, gbc_nombreIn);
		
		Label label_2 = new Label("Apellidos:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 3;
		add(label_2, gbc_label_2);
		
		TextField apellidosIn = new TextField();
		GridBagConstraints gbc_apellidosIn = new GridBagConstraints();
		gbc_apellidosIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidosIn.insets = new Insets(0, 0, 5, 5);
		gbc_apellidosIn.gridx = 2;
		gbc_apellidosIn.gridy = 3;
		add(apellidosIn, gbc_apellidosIn);
		
		Label label_3 = new Label("Email:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 4;
		add(label_3, gbc_label_3);
		
		TextField mailIn = new TextField();
		GridBagConstraints gbc_mailIn = new GridBagConstraints();
		gbc_mailIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailIn.insets = new Insets(0, 0, 5, 5);
		gbc_mailIn.gridx = 2;
		gbc_mailIn.gridy = 4;
		add(mailIn, gbc_mailIn);
		
		Label label_4 = new Label("Teléfono:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 5;
		add(label_4, gbc_label_4);
		
		TextField telefonoIn = new TextField();
		GridBagConstraints gbc_telefonoIn = new GridBagConstraints();
		gbc_telefonoIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoIn.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoIn.gridx = 2;
		gbc_telefonoIn.gridy = 5;
		add(telefonoIn, gbc_telefonoIn);
		
		chckbxNewCheckBox = new JCheckBox("Admin");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 6;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudOperations.insertarUsuario(null, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		add(btnNewButton, gbc_btnNewButton);
		
		Panel panel_1 = new Panel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 7;
		add(panel_1, gbc_panel_1);
		
		Panel panel_2 = new Panel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 7;
		add(panel_2, gbc_panel_2);

		
	}

}
