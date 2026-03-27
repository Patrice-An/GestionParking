package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfoSeGarerVoirie;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import modele.Tarif;
import modele.Vehicule;
import modele.Zone;

public class InfoSeGarerVoirie extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<Vehicule> choixVehicule;
	private JComboBox<Zone> comboZone;
	private JComboBox<Tarif> comboTarif;
	private App app;

	/**
	 * Launch the application.
	 * @param App 
	 */
	public InfoSeGarerVoirie(App app) {
		this.app = app;
		this.initialize();
		
	}


	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		if (app.getUtilisateur() == null) {
			return;
		}
		ControleurInfoSeGarerVoirie controleur = new ControleurInfoSeGarerVoirie(this, app);
		setBounds(100, 100, 450, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));

		JPanel panneauNord = new JPanel();
		this.add(panneauNord, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Garer en voirie");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		panneauNord.add(lblNewLabel);

		JPanel panneauCentre = new JPanel();
		this.add(panneauCentre, BorderLayout.CENTER);
		panneauCentre.setLayout(new BorderLayout(0, 0));

		JLabel titreDonnees = new JLabel("Sélectionner un véhicule");
		titreDonnees.setFont(new Font("Tahoma", Font.BOLD, 14));
		panneauCentre.add(titreDonnees, BorderLayout.NORTH);
		
		JPanel listeInfos = new JPanel();
		panneauCentre.add(listeInfos, BorderLayout.CENTER);
		listeInfos.setLayout(new GridLayout(6, 2, 0, 0));
		
		this.choixVehicule = new JComboBox<>();
		build();
		listeInfos.add(choixVehicule);
		
		JLabel labelSelectionVoirie = new JLabel("Sélectionnez la zone utilisé :");
		labelSelectionVoirie.setFont(new Font("Tahoma", Font.BOLD, 14));
		listeInfos.add(labelSelectionVoirie);

		this.comboZone = new JComboBox<>();
		this.comboZone.addItemListener(controleur.controleurVoirieTarif);
		DefaultComboBoxModel<Zone> voirieModel = new DefaultComboBoxModel<>();
		for (Zone z : controleur.getZones()) {
			voirieModel.addElement(z);
		}
		this.comboZone.setModel(voirieModel);
		listeInfos.add(this.comboZone);
		
		this.comboTarif = new JComboBox<>();
		controleur.updateComboTarif();
		listeInfos.add(this.comboTarif);

		JPanel panneauSud = new JPanel();
		this.add(panneauSud, BorderLayout.SOUTH);
		panneauSud.setLayout(new BorderLayout(0, 0));

		JButton btnValider = new JButton("Valider");
		btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
		btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
		btnValider.addActionListener(controleur);
		panneauSud.add(btnValider, BorderLayout.EAST);

		JButton btnAnnuler = new JButton("Annuler");
		panneauSud.add(btnAnnuler, BorderLayout.WEST);
		btnAnnuler.addActionListener(controleur);

		JPanel panel = new JPanel();
		panneauSud.add(panel, BorderLayout.NORTH);

	}

	public JComboBox<Vehicule> getComboVehicule() {
		return choixVehicule;
	}

	public JComboBox<Zone> getComboZone() {
		return comboZone;
	}

	public JComboBox<Tarif> getComboTarif() {
		return comboTarif;
	}
	
	private void build() {
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<>();
		for (Vehicule v : app.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);
	}
	
	public void refresh() {
		choixVehicule.removeAllItems();
		build();
	}
}
