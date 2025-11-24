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
	public FenRecapitulatif(String heureArrive, String heureDepart, String typeVehicule, String plaqueImma, String parking) {
		try {
			this.initialize(heureArrive, heureDepart, typeVehicule, plaqueImma, parking);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize(String heureArrive, String heureDepart, String typeVehicule, String plaqueImma, String parking) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);



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

		panelCentre.add(new JLabel("Heure d'arrivée :"));
		panelCentre.add(new JLabel(heureArrive));

		panelCentre.add(new JLabel("Heure Départ :"));
		panelCentre.add(new JLabel(heureDepart));

		panelCentre.add(new JLabel("Immatriculation :"));
		panelCentre.add(new JLabel(plaqueImma));

		panelCentre.add(new JLabel("Type de véhicule :"));
		panelCentre.add(new JLabel(typeVehicule));

		panelCentre.add(new JLabel("Parking choisi :"));
		panelCentre.add(new JLabel(parking));

		JPanel panelSud = new JPanel();
		panelSud.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panelSud, BorderLayout.SOUTH);

		JButton btnConfirmer = new JButton("Confirmer");
		panelSud.add(btnConfirmer);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
}

}
