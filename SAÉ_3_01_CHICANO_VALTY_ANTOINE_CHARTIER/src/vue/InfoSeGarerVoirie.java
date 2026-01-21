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
	private FenetrePrincipal fenetrePrincipal;

	/**
	 * Launch the application.
	 * @param fenetrePrincipal 
	 */
	public InfoSeGarerVoirie(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.initialize();
		
	}


	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		if (fenetrePrincipal.getUtilisateur() == null) {
			return;
		}
		ControleurInfoSeGarerVoirie controleur = new ControleurInfoSeGarerVoirie(this, fenetrePrincipal);
		setBounds(100, 100, 450, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));

		JPanel PanneauNord = new JPanel();
		this.add(PanneauNord, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Garer en voirie");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		PanneauNord.add(lblNewLabel);

		JPanel PanneauCentre = new JPanel();
		this.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new BorderLayout(0, 0));

		JLabel TitreDonnees = new JLabel("Sélectionner un véhicule");
		TitreDonnees.setFont(new Font("Tahoma", Font.BOLD, 14));
		PanneauCentre.add(TitreDonnees, BorderLayout.NORTH);
		
		JPanel ListeInfos = new JPanel();
		PanneauCentre.add(ListeInfos, BorderLayout.CENTER);
		ListeInfos.setLayout(new GridLayout(6, 2, 0, 0));
		
		/*this.choixVehicule = new JComboBox<Vehicule>();
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<Vehicule>();
		for (Vehicule v : fenetrePrincipal.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);*/
		this.choixVehicule = new JComboBox<Vehicule>();
		build();
		ListeInfos.add(choixVehicule);
		
		JLabel LabelSelectionVoirie = new JLabel("Sélectionnez la zone utilisé :");
		LabelSelectionVoirie.setFont(new Font("Tahoma", Font.BOLD, 14));
		ListeInfos.add(LabelSelectionVoirie);

		this.comboZone = new JComboBox<Zone>();
		this.comboZone.addItemListener(controleur.controleurVoirieTarif);
		DefaultComboBoxModel<Zone> voirieModel = new DefaultComboBoxModel<Zone>();
		for (Zone z : controleur.getZones()) {
			voirieModel.addElement(z);
		}
		this.comboZone.setModel(voirieModel);
		ListeInfos.add(this.comboZone);
		
		this.comboTarif = new JComboBox<Tarif>();
		controleur.updateComboTarif();
		ListeInfos.add(this.comboTarif);

		JPanel PanneauSud = new JPanel();
		this.add(PanneauSud, BorderLayout.SOUTH);
		PanneauSud.setLayout(new BorderLayout(0, 0));

		JButton btnValider = new JButton("Valider");
		btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
		btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
		btnValider.addActionListener(controleur);
		PanneauSud.add(btnValider, BorderLayout.EAST);

		JButton btnAnnuler = new JButton("Annuler");
		PanneauSud.add(btnAnnuler, BorderLayout.WEST);
		btnAnnuler.addActionListener(controleur);

		JPanel panel = new JPanel();
		PanneauSud.add(panel, BorderLayout.NORTH);

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
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<Vehicule>();
		for (Vehicule v : fenetrePrincipal.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);
	}
	
	public void refresh() {
		choixVehicule.removeAllItems();
		build();
	}
}
