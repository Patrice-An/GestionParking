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
	private final List<String> listeDonnees;

	/**
	 * Create the panel.
	 */
	public Facture(App fenPrincipal, ControleurHistorique accesDonnees) {
		String policeTahoma = "Tahoma";
		this.listeDonnees = accesDonnees.getDonneesFacture();
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setFont(new Font(policeTahoma, Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		GridBagLayout gblpaneCenter = new GridBagLayout();
		gblpaneCenter.columnWidths = new int[]{46, 124, 96, 0};
		gblpaneCenter.rowHeights = new int[]{39, 17, 17, 17, 17, 39, 19, 0};
		gblpaneCenter.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblpaneCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		paneCenter.setLayout(gblpaneCenter);
		
		JLabel labelNom = new JLabel("Nom");
		labelNom.setText(listeDonnees.get(0));
		labelNom.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelNom = new GridBagConstraints();
		gbclabelNom.anchor = GridBagConstraints.NORTHWEST;
		gbclabelNom.insets = new Insets(0, 0, 5, 5);
		gbclabelNom.gridx = 1;
		gbclabelNom.gridy = 1;
		paneCenter.add(labelNom, gbclabelNom);
		
		JLabel labelPrenom = new JLabel("Prenom");
		labelPrenom.setText(listeDonnees.get(1));
		labelPrenom.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelPrenom = new GridBagConstraints();
		gbclabelPrenom.anchor = GridBagConstraints.NORTHWEST;
		gbclabelPrenom.insets = new Insets(0, 0, 5, 0);
		gbclabelPrenom.gridx = 2;
		gbclabelPrenom.gridy = 1;
		paneCenter.add(labelPrenom, gbclabelPrenom);
		
		JLabel labelVehicule = new JLabel("Vehicule :");
		labelVehicule.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelVehicule = new GridBagConstraints();
		gbclabelVehicule.anchor = GridBagConstraints.NORTHWEST;
		gbclabelVehicule.insets = new Insets(0, 0, 5, 5);
		gbclabelVehicule.gridx = 1;
		gbclabelVehicule.gridy = 2;
		paneCenter.add(labelVehicule, gbclabelVehicule);
		
		JLabel labelVehiculeAffiche = new JLabel("Vehicule");
		labelVehiculeAffiche.setText(listeDonnees.get(2));
		labelVehiculeAffiche.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelVehiculeAffiche = new GridBagConstraints();
		gbclabelVehiculeAffiche.anchor = GridBagConstraints.NORTHWEST;
		gbclabelVehiculeAffiche.insets = new Insets(0, 0, 5, 0);
		gbclabelVehiculeAffiche.gridx = 2;
		gbclabelVehiculeAffiche.gridy = 2;
		paneCenter.add(labelVehiculeAffiche, gbclabelVehiculeAffiche);
		
		JLabel labelParking = new JLabel("Parking ");
		labelParking.setText("Parking "+listeDonnees.get(3));
		labelParking.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelParking = new GridBagConstraints();
		gbclabelParking.anchor = GridBagConstraints.NORTHWEST;
		gbclabelParking.insets = new Insets(0, 0, 5, 5);
		gbclabelParking.gridx = 1;
		gbclabelParking.gridy = 3;
		paneCenter.add(labelParking, gbclabelParking);
		
		JLabel labelArrivee = new JLabel("De : arrivee");
		labelArrivee.setText("De : "+listeDonnees.get(4));
		labelArrivee.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelArrivee = new GridBagConstraints();
		gbclabelArrivee.anchor = GridBagConstraints.NORTHWEST;
		gbclabelArrivee.insets = new Insets(0, 0, 5, 5);
		gbclabelArrivee.gridx = 1;
		gbclabelArrivee.gridy = 4;
		paneCenter.add(labelArrivee, gbclabelArrivee);
		
		JLabel labelDepart = new JLabel("A : depart");
		labelDepart.setText("A : "+listeDonnees.get(4));
		labelDepart.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		GridBagConstraints gbclabelDepart = new GridBagConstraints();
		gbclabelDepart.anchor = GridBagConstraints.NORTHWEST;
		gbclabelDepart.insets = new Insets(0, 0, 5, 0);
		gbclabelDepart.gridx = 2;
		gbclabelDepart.gridy = 4;
		paneCenter.add(labelDepart, gbclabelDepart);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbchorizontalBox = new GridBagConstraints();
		gbchorizontalBox.anchor = GridBagConstraints.NORTHWEST;
		gbchorizontalBox.insets = new Insets(0, 0, 0, 5);
		gbchorizontalBox.gridx = 1;
		gbchorizontalBox.gridy = 6;
		paneCenter.add(horizontalBox, gbchorizontalBox);
		
		JLabel labelPrix = new JLabel("Prix : ");
		labelPrix.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		horizontalBox.add(labelPrix);
		
		JLabel labelPrixNombre = new JLabel("Nombre");
		labelPrixNombre.setText("10€");
		labelPrixNombre.setFont(new Font(policeTahoma, Font.PLAIN, 14));
		horizontalBox.add(labelPrixNombre);
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		paneSouth.setLayout(new BorderLayout(0, 0));

	}

}
