package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controleur.ControleurListeParking;
import modele.Parking;
import modele.dao.DaoParking;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class ListeParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ListeParking() {
		ControleurListeParking controleur = new ControleurListeParking(this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		paneCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel paneSearch = new JPanel();
		paneCenter.add(paneSearch, BorderLayout.NORTH);
		paneSearch.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		paneSearch.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(controleur);
		paneSearch.add(btnSearch, BorderLayout.EAST);
		
		JLabel labelTitre = new JLabel("Parking disponibles");
		labelTitre.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelTitre, BorderLayout.NORTH);
		
		JPanel paneEast = new JPanel();
		add(paneEast, BorderLayout.EAST);
		
		JPanel paneWest = new JPanel();
		add(paneWest, BorderLayout.WEST);
		
		DefaultTableModel modele = new DefaultTableModel();
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
						Integer.toString(i.getplacesMax()), Integer.toString(i.getplacesMaxMoto()), Integer.toString(i.getplacesMaxElectrique())};
				modele.addRow(newRow);
			}
		} catch(SQLException eee) {
			//empty
		}
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setModel(modele);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setPreferredSize(new Dimension(800, 400));
		table.setMinimumSize(new Dimension(400, 200));
		table.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(table);

	}

}
