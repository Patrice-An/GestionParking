package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.*;

import controleur.ControleurGestionVehicule;
import modele.Vehicule;

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
		
		ControleurGestionVehicule controleur = new ControleurGestionVehicule(this, fenetre);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel Title = new JLabel("Gestion des véhicules");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Title, BorderLayout.NORTH);
		
		JPanel paneSearch = new JPanel();
		panel.add(paneSearch, BorderLayout.WEST);
		paneSearch.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		paneSearch.add(searchField, BorderLayout.WEST);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		paneSearch.add(btnSearch, BorderLayout.CENTER);
		
		JPanel marginTop = new JPanel();
		paneSearch.add(marginTop, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		paneSearch.add(panel_7, BorderLayout.EAST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnAjout = new JButton("Ajouter un véhicule");
		btnAjout.addActionListener(controleur);
		panel_7.add(btnAjout, BorderLayout.EAST);
		
		JPanel marginBtwinBtn = new JPanel();
		marginBtwinBtn.setPreferredSize(new Dimension(100, 10));
		panel_7.add(marginBtwinBtn, BorderLayout.CENTER);
		
		JPanel marginTopCenter = new JPanel();
		panel.add(marginTopCenter, BorderLayout.SOUTH);
		
		JPanel marginLeft = new JPanel();
		add(marginLeft, BorderLayout.WEST);
		
		JPanel marginRight = new JPanel();
		add(marginRight, BorderLayout.EAST);
		
		table = new JTable();
		DefaultTableModel modele = new DefaultTableModel();
		modele.addColumn("Désignation");
		modele.addColumn("Type");
		modele.addColumn("Hauteur");
		modele.addColumn("Immatriculation");
		modele.addColumn("");
		String[] titleRow = {"Désignation","Type","Hauteur","Immatriculation",""};
		modele.addRow(titleRow);
		table.setModel(modele);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		add(table, BorderLayout.CENTER);
		
		JPanel marginBottom = new JPanel();
		add(marginBottom, BorderLayout.SOUTH);
		
			Set<Vehicule> res = fenetre.getUtilisateur().getVehicules();
			for (Vehicule i:res) {
				JButton button = new JButton("Modifier");
				button.addActionListener(controleur);
				Object[] newRow = {i.getDésignation(), i.getType().toString(), Float.toString(i.getHauteur())+" m", i.getImmatriculation(), button};
				modele.addRow(newRow);
				
			}
	}

}
