package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FenRecapitulatif extends JPanel {

	/**
	 * Launch the application.
	 * @param App 
	 */
	public FenRecapitulatif(App app) {
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

		JLabel label6 = new JLabel("Heure d'arrivée :");
		panelCentre.add(label6);
		JLabel label7 = new JLabel();
		label6.setLabelFor(label7);
		panelCentre.add(label7);

		JLabel label4 = new JLabel("Heure Départ :");
		panelCentre.add(label4);
		JLabel label5 = new JLabel();
		label4.setLabelFor(label5);
		panelCentre.add(label5);

		JLabel label = new JLabel("Immatriculation :");
		panelCentre.add(label);
		JLabel label1 = new JLabel();
		label.setLabelFor(label1);
		panelCentre.add(label1);

		JLabel label2 = new JLabel("Parking choisi :");
		panelCentre.add(label2);
		JLabel label3 = new JLabel();
		label2.setLabelFor(label3);
		panelCentre.add(label3);

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



