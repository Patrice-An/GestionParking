package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FenRecapitulatif extends JPanel {

	private FenetrePrincipal fenetrePrincipal;

	/**
	 * Launch the application.
	 * @param fenetrePrincipal 
	 */
	public FenRecapitulatif(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		
		this.initialize();
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	private void initialize() {

		
		setBounds(100, 100, 400, 250);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new GridLayout(5, 2, 5, 5));
		this.add(panelCentre, BorderLayout.CENTER);

		JLabel label_6 = new JLabel("Heure d'arrivée :");
		panelCentre.add(label_6);
		JLabel label_7 = new JLabel();
		label_6.setLabelFor(label_7);
		panelCentre.add(label_7);

		JLabel label_4 = new JLabel("Heure Départ :");
		panelCentre.add(label_4);
		JLabel label_5 = new JLabel();
		label_4.setLabelFor(label_5);
		panelCentre.add(label_5);

		JLabel label = new JLabel("Immatriculation :");
		panelCentre.add(label);
		JLabel label_1 = new JLabel();
		label.setLabelFor(label_1);
		panelCentre.add(label_1);

		//panelCentre.add(new JLabel("Type de véhicule :"));
		//panelCentre.add(new JLabel(typeVehicule.toString()));

		JLabel label_2 = new JLabel("Parking choisi :");
		panelCentre.add(label_2);
		JLabel label_3 = new JLabel();
		label_2.setLabelFor(label_3);
		panelCentre.add(label_3);

		JPanel panelSud = new JPanel();
		panelSud.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(panelSud, BorderLayout.SOUTH);

		JButton btnConfirmer = new JButton("Confirmer");
		panelSud.add(btnConfirmer);

		JPanel panelHaut = new JPanel();
		this.add(panelHaut, BorderLayout.NORTH);

		JLabel lbTitre = new JLabel("VOUS ÊTES STATIONNÉ !");
		panelHaut.add(lbTitre);
	}
}



