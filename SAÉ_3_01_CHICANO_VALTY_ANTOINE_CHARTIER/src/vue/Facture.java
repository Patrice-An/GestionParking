package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import javax.swing.Box;
import javax.swing.border.LineBorder;

import controleur.ControleurHistorique;

import java.awt.Color;
import javax.swing.JButton;

public class Facture extends JPanel {

	private static final long serialVersionUID = 1L;
	private FenetrePrincipal fenPrincipal;
	private ControleurHistorique accesDonnees;
	private List<String> listeDonnees;
	//private ControleurFacture controleur;

	/**
	 * Create the panel.
	 */
	public Facture(FenetrePrincipal fenPrincipal, ControleurHistorique accesDonnees) {
		this.listeDonnees = accesDonnees.getDonneesFacture();
		this.fenPrincipal = fenPrincipal;
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		paneCenter.setLayout(new MigLayout("", "[][][]", "[][][][][][][]"));
		
		JLabel LabelNom = new JLabel("Nom");
		LabelNom.setText(listeDonnees.get(0));
		LabelNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelNom, "cell 1 1");
		
		JLabel LabelPrenom = new JLabel("Prenom");
		LabelPrenom.setText(listeDonnees.get(1));
		LabelPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelPrenom, "cell 2 1");
		
		JLabel LabelVehicule = new JLabel("Vehicule :");
		LabelVehicule.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelVehicule, "cell 1 2");
		
		JLabel LabelVehiculeAffiche = new JLabel("Vehicuel");
		LabelVehiculeAffiche.setText(listeDonnees.get(2));
		LabelVehiculeAffiche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelVehiculeAffiche, "cell 2 2");
		
		JLabel LabelParking = new JLabel("Parking ");
		LabelParking.setText("Parking "+listeDonnees.get(3));
		LabelParking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelParking, "cell 1 3");
		
		JLabel LabelArrivee = new JLabel("De : arrivee");
		LabelArrivee.setText("De : "+listeDonnees.get(4));
		LabelArrivee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelArrivee, "cell 1 4");
		
		JLabel LabelDepart = new JLabel("A : depart");
		LabelDepart.setText("A : "+listeDonnees.get(5));
		LabelDepart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paneCenter.add(LabelDepart, "cell 2 4");
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(horizontalBox, "cell 1 6");
		
		JLabel LabelPrix = new JLabel("Prix : ");
		LabelPrix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(LabelPrix);
		
		JLabel LabelPrixNombre = new JLabel("Nombre");
		LabelPrixNombre.setText("calcul tarf");
		LabelPrixNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalBox.add(LabelPrixNombre);
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		paneSouth.setLayout(new BorderLayout(0, 0));

	}

}
