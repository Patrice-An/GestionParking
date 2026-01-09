package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurListeParking;
import modele.Parking;
import modele.Zone;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListeParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private JPanel paneCenter;
	private FenetrePrincipal fenetreprincipal;
	DefaultTableModel modele = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public ListeParking() {
		ControleurListeParking controleur = new ControleurListeParking(this, this.fenetreprincipal);
		setLayout(new BorderLayout(0, 0));
		
		paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		paneCenter.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		paneCenter.add(table, BorderLayout.CENTER);
		
		JPanel paneSearch = new JPanel();
		paneCenter.add(paneSearch, BorderLayout.NORTH);
		paneSearch.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		paneSearch.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(controleur);
		paneSearch.add(btnSearch, BorderLayout.EAST);
		
		JLabel LabelTitre = new JLabel("Parking disponibles");
		LabelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		add(LabelTitre, BorderLayout.NORTH);
		
		JPanel paneEast = new JPanel();
		add(paneEast, BorderLayout.EAST);
		
		JPanel paneWest = new JPanel();
		add(paneWest, BorderLayout.WEST);
		
		
		this.faireTableauParking();
		
	}
	
	public void faireTableauParking() {
		paneCenter.remove(table);
		
		modele.addColumn("Nom");
		modele.addColumn("Adresse");
		modele.addColumn("Hauteur");
		modele.addColumn("Nombre de place");
		modele.addColumn("Places moto");
		modele.addColumn("Places compatibles électrique");
		String[] titleRow = {"Nom", "Adresse", "Hauteur", "Nombre de place", "Places moto", "Places compatibles électrique"};
		modele.addRow(titleRow);
		
		try {
			List<Parking> res = new DaoParking().findAll();
			for (Parking i:res) {
				String[] newRow = {i.getNom(), i.getAdresse(), Float.toString(i.getHauteur()), 
						Integer.toString(i.getPlaces_max()), Integer.toString(i.getPlace_max_moto()), Integer.toString(i.getPlace_max_electrique())};
				modele.addRow(newRow);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(modele);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setPreferredSize(new Dimension(800, 400));
		table.setMinimumSize(new Dimension(400, 200));
		table.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(table);
		
	}

}
