package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserModify extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public UserModify() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 106, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		add(list, gbc_list);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton deleteBt = new JButton("Borrar");
		GridBagConstraints gbc_deleteBt = new GridBagConstraints();
		gbc_deleteBt.insets = new Insets(0, 0, 0, 5);
		gbc_deleteBt.gridx = 1;
		gbc_deleteBt.gridy = 1;
		panel.add(deleteBt, gbc_deleteBt);
		
		JButton upgradeBt = new JButton("Dar permisos");
		upgradeBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_upgradeBt = new GridBagConstraints();
		gbc_upgradeBt.insets = new Insets(0, 0, 0, 5);
		gbc_upgradeBt.gridx = 3;
		gbc_upgradeBt.gridy = 1;
		panel.add(upgradeBt, gbc_upgradeBt);
		
		JButton donwngradeBt = new JButton("Quitar permisos");
		GridBagConstraints gbc_donwngradeBt = new GridBagConstraints();
		gbc_donwngradeBt.insets = new Insets(0, 0, 0, 5);
		gbc_donwngradeBt.gridx = 5;
		gbc_donwngradeBt.gridy = 1;
		panel.add(donwngradeBt, gbc_donwngradeBt);

	}

}
