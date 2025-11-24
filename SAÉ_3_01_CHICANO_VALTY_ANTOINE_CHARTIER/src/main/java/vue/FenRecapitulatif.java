package main.java.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Type_vehicule;

public class FenRecapitulatif extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public FenRecapitulatif(String heureArrive, String heureDepart, String plaqueImma, String parking) {
		try {
			this.initialize(heureArrive, heureDepart, plaqueImma, parking);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize(String heureArrive, String heureDepart, String plaqueImma, String parking) {
		
		setTitle("Récapitulatif");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelCentre = new JPanel();
		panelCentre.setLayout(new GridLayout(5, 2, 5, 5));
		contentPane.add(panelCentre, BorderLayout.CENTER);

		JLabel label_6 = new JLabel("Heure d'arrivée :");
		panelCentre.add(label_6);
		JLabel label_7 = new JLabel(heureArrive);
		label_6.setLabelFor(label_7);
		panelCentre.add(label_7);

		JLabel label_4 = new JLabel("Heure Départ :");
		panelCentre.add(label_4);
		JLabel label_5 = new JLabel(heureDepart);
		label_4.setLabelFor(label_5);
		panelCentre.add(label_5);

		JLabel label = new JLabel("Immatriculation :");
		panelCentre.add(label);
		JLabel label_1 = new JLabel(plaqueImma);
		label.setLabelFor(label_1);
		panelCentre.add(label_1);

		//panelCentre.add(new JLabel("Type de véhicule :"));
		//panelCentre.add(new JLabel(typeVehicule.toString()));

		JLabel label_2 = new JLabel("Parking choisi :");
		panelCentre.add(label_2);
		JLabel label_3 = new JLabel(parking);
		label_2.setLabelFor(label_3);
		panelCentre.add(label_3);

		JPanel panelSud = new JPanel();
		panelSud.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSud, BorderLayout.SOUTH);

		JButton btnConfirmer = new JButton("Confirmer");
		panelSud.add(btnConfirmer);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}



