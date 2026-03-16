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
	private JTable table_1;
	private JTextField textField;
	private FenetrePrincipal fenetreprincipal;

	/**
	 * Create the panel.
	 */
	public ListeParking() {
		ControleurListeParking controleur = new ControleurListeParking(this, this.fenetreprincipal);
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
		
		JLabel LabelTitre = new JLabel("Parking disponibles");
		LabelTitre.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		LabelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		add(LabelTitre, BorderLayout.NORTH);
		
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
						Integer.toString(i.getPlaces_max()), Integer.toString(i.getPlace_max_moto()), Integer.toString(i.getPlace_max_electrique())};
				modele.addRow(newRow);
			}
		} catch(SQLException _) {
			//empty
		}
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setRowSelectionAllowed(false);
		table_1.setModel(modele);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setPreferredSize(new Dimension(800, 400));
		table_1.setMinimumSize(new Dimension(400, 200));
		table_1.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(table_1);

	}

}
