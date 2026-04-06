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

	private static final long serialVersionUID = 1L;;
	private List<String> listeDonnees;
	// private ControleurFacture controleur;

	/**
	 * Create the panel.
	 */
	public Facture(ControleurHistorique accesDonnees) {
		boolean isParking = accesDonnees.isParking();
		this.listeDonnees = accesDonnees.getDonneesFacture();
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);

		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		GridBagLayout gbl_paneCenter = new GridBagLayout();
		gbl_paneCenter.columnWidths = new int[] { 46, 124, 96, 0 };
		gbl_paneCenter.rowHeights = new int[] { 39, 17, 17, 17, 17, 39, 19, 0 };
		gbl_paneCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_paneCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		paneCenter.setLayout(gbl_paneCenter);

		JLabel LabelNom = new JLabel("Nom");
		LabelNom.setText(listeDonnees.get(0));
		LabelNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelNom = new GridBagConstraints();
		gbc_LabelNom.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelNom.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNom.gridx = 1;
		gbc_LabelNom.gridy = 1;
		paneCenter.add(LabelNom, gbc_LabelNom);

		JLabel LabelPrenom = new JLabel("Prenom");
		LabelPrenom.setText(listeDonnees.get(1));
		LabelPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelPrenom = new GridBagConstraints();
		gbc_LabelPrenom.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelPrenom.insets = new Insets(0, 0, 5, 0);
		gbc_LabelPrenom.gridx = 2;
		gbc_LabelPrenom.gridy = 1;
		paneCenter.add(LabelPrenom, gbc_LabelPrenom);

		JLabel LabelVehicule = new JLabel("Vehicule :");
		LabelVehicule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelVehicule = new GridBagConstraints();
		gbc_LabelVehicule.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelVehicule.insets = new Insets(0, 0, 5, 5);
		gbc_LabelVehicule.gridx = 1;
		gbc_LabelVehicule.gridy = 2;
		paneCenter.add(LabelVehicule, gbc_LabelVehicule);

		JLabel LabelVehiculeAffiche = new JLabel("Vehicule");
		LabelVehiculeAffiche.setText(listeDonnees.get(2));
		LabelVehiculeAffiche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelVehiculeAffiche = new GridBagConstraints();
		gbc_LabelVehiculeAffiche.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelVehiculeAffiche.insets = new Insets(0, 0, 5, 0);
		gbc_LabelVehiculeAffiche.gridx = 2;
		gbc_LabelVehiculeAffiche.gridy = 2;
		paneCenter.add(LabelVehiculeAffiche, gbc_LabelVehiculeAffiche);
		JLabel LabelPZ;
		if (isParking) {
			LabelPZ = new JLabel("Parking " + listeDonnees.get(3));
		} else {
			LabelPZ = new JLabel("Zone " + listeDonnees.get(3));
		}
		LabelPZ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelPZ = new GridBagConstraints();
		gbc_LabelPZ.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelPZ.insets = new Insets(0, 0, 5, 5);
		gbc_LabelPZ.gridx = 1;
		gbc_LabelPZ.gridy = 3;
		paneCenter.add(LabelPZ, gbc_LabelPZ);

		JLabel LabelArrivee = new JLabel("De : arrivee");
		LabelArrivee.setText("De : " + listeDonnees.get(4));
		LabelArrivee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelArrivee = new GridBagConstraints();
		gbc_LabelArrivee.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelArrivee.insets = new Insets(0, 0, 5, 5);
		gbc_LabelArrivee.gridx = 1;
		gbc_LabelArrivee.gridy = 4;
		paneCenter.add(LabelArrivee, gbc_LabelArrivee);

		JLabel LabelDepart = new JLabel("A : depart");
		LabelDepart.setText("A : " + listeDonnees.get(4));
		LabelDepart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelDepart = new GridBagConstraints();
		gbc_LabelDepart.anchor = GridBagConstraints.NORTHWEST;
		gbc_LabelDepart.insets = new Insets(0, 0, 5, 0);
		gbc_LabelDepart.gridx = 2;
		gbc_LabelDepart.gridy = 4;
		paneCenter.add(LabelDepart, gbc_LabelDepart);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_horizontalBox.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalBox.gridx = 1;
		gbc_horizontalBox.gridy = 6;
		paneCenter.add(horizontalBox, gbc_horizontalBox);

		JLabel LabelPrix = new JLabel("Prix : ");
		LabelPrix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(LabelPrix);

		JLabel LabelPrixNombre = new JLabel("Nombre");
		LabelPrixNombre.setText("10€");
		LabelPrixNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(LabelPrixNombre);

		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		paneSouth.setLayout(new BorderLayout(0, 0));

	}

}
