package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.border.LineBorder;

import controleur.ControleurHistorique;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Facture extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<String> listeDonnees;
	private static final String FONT = "Tahoma";

	/**
	 * Create the panel.
	 */
	public Facture(ControleurHistorique accesDonnees) {
		this.listeDonnees = accesDonnees.getDonneesFacture();
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setFont(new Font(FONT, Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		GridBagLayout gblPaneCenter = new GridBagLayout();
		gblPaneCenter.columnWidths = new int[]{46, 124, 96, 0};
		gblPaneCenter.rowHeights = new int[]{39, 17, 17, 17, 17, 39, 19, 0};
		gblPaneCenter.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblPaneCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		paneCenter.setLayout(gblPaneCenter);
		
		JLabel labelNom = new JLabel("Nom");
		labelNom.setText(listeDonnees.get(0));
		labelNom.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelNom = new GridBagConstraints();
		gbcLabelNom.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelNom.insets = new Insets(0, 0, 5, 5);
		gbcLabelNom.gridx = 1;
		gbcLabelNom.gridy = 1;
		paneCenter.add(labelNom, gbcLabelNom);
		
		JLabel labelPrenom = new JLabel("Prenom");
		labelPrenom.setText(listeDonnees.get(1));
		labelPrenom.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelPrenom = new GridBagConstraints();
		gbcLabelPrenom.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelPrenom.insets = new Insets(0, 0, 5, 0);
		gbcLabelPrenom.gridx = 2;
		gbcLabelPrenom.gridy = 1;
		paneCenter.add(labelPrenom, gbcLabelPrenom);
		
		JLabel labelVehicule = new JLabel("Vehicule :");
		labelVehicule.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelVehicule = new GridBagConstraints();
		gbcLabelVehicule.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelVehicule.insets = new Insets(0, 0, 5, 5);
		gbcLabelVehicule.gridx = 1;
		gbcLabelVehicule.gridy = 2;
		paneCenter.add(labelVehicule, gbcLabelVehicule);
		
		JLabel labelVehiculeAffiche = new JLabel("Vehicule");
		labelVehiculeAffiche.setText(listeDonnees.get(2));
		labelVehiculeAffiche.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelVehiculeAffiche = new GridBagConstraints();
		gbcLabelVehiculeAffiche.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelVehiculeAffiche.insets = new Insets(0, 0, 5, 0);
		gbcLabelVehiculeAffiche.gridx = 2;
		gbcLabelVehiculeAffiche.gridy = 2;
		paneCenter.add(labelVehiculeAffiche, gbcLabelVehiculeAffiche);
		
		JLabel labelParking = new JLabel("Parking ");
		labelParking.setText("Parking "+listeDonnees.get(3));
		labelParking.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelParking = new GridBagConstraints();
		gbcLabelParking.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelParking.insets = new Insets(0, 0, 5, 5);
		gbcLabelParking.gridx = 1;
		gbcLabelParking.gridy = 3;
		paneCenter.add(labelParking, gbcLabelParking);
		
		JLabel labelArrivee = new JLabel("De : arrivee");
		labelArrivee.setText("De : "+listeDonnees.get(4));
		labelArrivee.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelArrivee = new GridBagConstraints();
		gbcLabelArrivee.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelArrivee.insets = new Insets(0, 0, 5, 5);
		gbcLabelArrivee.gridx = 1;
		gbcLabelArrivee.gridy = 4;
		paneCenter.add(labelArrivee, gbcLabelArrivee);
		
		JLabel labelDepart = new JLabel("A : depart");
		labelDepart.setText("A : "+listeDonnees.get(4));
		labelDepart.setFont(new Font(FONT, Font.PLAIN, 14));
		GridBagConstraints gbcLabelDepart = new GridBagConstraints();
		gbcLabelDepart.anchor = GridBagConstraints.NORTHWEST;
		gbcLabelDepart.insets = new Insets(0, 0, 5, 0);
		gbcLabelDepart.gridx = 2;
		gbcLabelDepart.gridy = 4;
		paneCenter.add(labelDepart, gbcLabelDepart);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbcHorizontalBox = new GridBagConstraints();
		gbcHorizontalBox.anchor = GridBagConstraints.NORTHWEST;
		gbcHorizontalBox.insets = new Insets(0, 0, 0, 5);
		gbcHorizontalBox.gridx = 1;
		gbcHorizontalBox.gridy = 6;
		paneCenter.add(horizontalBox, gbcHorizontalBox);
		
		JLabel labelPrix = new JLabel("Prix : ");
		labelPrix.setFont(new Font(FONT, Font.PLAIN, 14));
		horizontalBox.add(labelPrix);
		
		JLabel labelPrixNombre = new JLabel("Nombre");
		labelPrixNombre.setText("10€");
		labelPrixNombre.setFont(new Font(FONT, Font.PLAIN, 14));
		horizontalBox.add(labelPrixNombre);
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		paneSouth.setLayout(new BorderLayout(0, 0));

	}

}
