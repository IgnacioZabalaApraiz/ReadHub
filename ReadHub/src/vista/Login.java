package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userIn;
	private JPasswordField passwordIn;

	/**
	 * Create the panel.
	 */
	public Login() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 2;
		gbc_panel_5.gridy = 0;
		add(panel_5, gbc_panel_5);
		
		JLabel user65 = new JLabel("Usuario");
		GridBagConstraints gbc_user65 = new GridBagConstraints();
		gbc_user65.insets = new Insets(0, 0, 5, 5);
		gbc_user65.anchor = GridBagConstraints.EAST;
		gbc_user65.gridx = 0;
		gbc_user65.gridy = 1;
		add(user65, gbc_user65);
		
		userIn = new JTextField();
		GridBagConstraints gbc_userIn = new GridBagConstraints();
		gbc_userIn.insets = new Insets(0, 0, 5, 5);
		gbc_userIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_userIn.gridx = 1;
		gbc_userIn.gridy = 1;
		add(userIn, gbc_userIn);
		userIn.setColumns(10);
		
		JLabel password = new JLabel("Contraseña");
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.anchor = GridBagConstraints.EAST;
		gbc_password.gridx = 0;
		gbc_password.gridy = 2;
		add(password, gbc_password);
		
		passwordIn = new JPasswordField();
		GridBagConstraints gbc_passwordIn = new GridBagConstraints();
		gbc_passwordIn.insets = new Insets(0, 0, 5, 5);
		gbc_passwordIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordIn.gridx = 1;
		gbc_passwordIn.gridy = 2;
		add(passwordIn, gbc_passwordIn);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);

	}

}
