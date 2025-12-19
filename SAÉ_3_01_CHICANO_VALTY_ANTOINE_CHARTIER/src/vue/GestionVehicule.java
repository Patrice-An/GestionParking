package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import modele.Vehicule;
import modele.Zone;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;

public class GestionVehicule extends JPanel {

	private static final long serialVersionUID = 1L;
	private FenetrePrincipal fenetre;
	private JTextField searchField;
	private JTable table;

	
	public GestionVehicule(FenetrePrincipal fenetre) {
		setMaximumSize(new Dimension(32767, 300));
		this.fenetre = fenetre;
		initialize();
	}
	/**
	 * Create the panel.
	 */
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel Title = new JLabel("Gestion des véhicules");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Title, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		panel_1.add(searchField, BorderLayout.WEST);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		panel_1.add(btnSearch, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.EAST);
		
		table = new JTable();
		DefaultTableModel modele = new DefaultTableModel();
		modele.addColumn("Désignation");
		modele.addColumn("Type");
		modele.addColumn("Hauteur");
		modele.addColumn("Immatriculation");
		String[] titleRow = {"Désignation","Type","Hauteur","Immatriculation"};
		modele.addRow(titleRow);
		table.setModel(modele);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		add(table, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		add(panel_6, BorderLayout.SOUTH);
		
			Set<Vehicule> res = fenetre.getUtilisateur().getVehicules();
			for (Vehicule i:res) {
				String[] newRow = {i.getDésignation(), i.getType().toString(), Float.toString(i.getHauteur())+" m", i.getImmatriculation()};
				modele.addRow(newRow);
			}
	}

}
