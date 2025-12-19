package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfosSeGarerParking;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import modele.Parking;
import modele.Type_vehicule;
import modele.Vehicule;
import modele.dao.DaoParking;

public class InfoSeGarerParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel SaisieHeureArrive;
	private JComboBox<Vehicule> choixVehicule;
	private JComboBox<Parking> DeroulantSelectionParking;
	private FenetrePrincipal fenetrePrincipal;
	/**
	 * Launch the application.
	 * @param fenetrePrincipal 
	 */
	public InfoSeGarerParking(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.initialize();
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		ControleurInfosSeGarerParking controleur = new ControleurInfosSeGarerParking(this, fenetrePrincipal);


		setBounds(100, 100, 450, 300);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(new BorderLayout(0, 0));

		JPanel PanneauNord = new JPanel();
		this.add(PanneauNord, BorderLayout.NORTH);

		JPanel PanneauCentre = new JPanel();
		this.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new BorderLayout(0, 0));

		JLabel TitreDonnees = new JLabel("Renseignez les données pour la simulation");
		TitreDonnees.setFont(new Font("Tahoma", Font.BOLD, 14));
		PanneauCentre.add(TitreDonnees, BorderLayout.NORTH);

		JPanel ListeInfos = new JPanel();
		PanneauCentre.add(ListeInfos, BorderLayout.CENTER);
		ListeInfos.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel LabelHeureArrive = new JLabel("Heure d'arrivée :");
		ListeInfos.add(LabelHeureArrive);

		LocalTime current = LocalTime.now();
		this.SaisieHeureArrive = new JLabel(String.valueOf(current.getHour()) + "H" + String.valueOf(current.getMinute()));

		ListeInfos.add(SaisieHeureArrive);

		JLabel LabelChoixVehicule = new JLabel("Choix véhicule :");
		ListeInfos.add(LabelChoixVehicule);

		this.choixVehicule = new JComboBox<Vehicule>();
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<Vehicule>();
		for (Vehicule v : fenetrePrincipal.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);
		ListeInfos.add(choixVehicule);

		JLabel LabelSelectionVoirie = new JLabel("Sélectionnez le Parking utilisé :");
		LabelSelectionVoirie.setFont(new Font("Tahoma", Font.BOLD, 14));
		ListeInfos.add(LabelSelectionVoirie);

		JLabel lblNewLabel = new JLabel(" ");
		ListeInfos.add(lblNewLabel);

		this.DeroulantSelectionParking = new JComboBox<Parking>();
		DefaultComboBoxModel<Parking> parkingModel = new DefaultComboBoxModel<Parking>();
		try {
			for (Parking p : new DaoParking().findAll()) {
				parkingModel.addElement(p);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		DeroulantSelectionParking.setModel(parkingModel);
		ListeInfos.add(DeroulantSelectionParking);

		JPanel PanneauSud = new JPanel();
		this.add(PanneauSud, BorderLayout.SOUTH);
		PanneauSud.setLayout(new BorderLayout(0, 0));

		JButton btnValider = new JButton("Valider");
		btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
		btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
		PanneauSud.add(btnValider, BorderLayout.EAST);
		btnValider.addActionListener(controleur);

		JPanel panel = new JPanel();
		PanneauSud.add(panel, BorderLayout.NORTH);

		JButton btnAnnuler = new JButton("Annuler");
		PanneauSud.add(btnAnnuler, BorderLayout.WEST);
		btnAnnuler.addActionListener(controleur);
	}

	public String getSaisieHeureArrive() {
		return SaisieHeureArrive.getText();
	}

	public String getSaisieDuree() {
		return SaisieDuree.getText();
	}

	public JComboBox<Vehicule> getSaisieImmatriculation() {
		return this.choixVehicule;
	}

	public String getParking(){
		return (String) DeroulantSelectionParking.getSelectedItem();
	}
}
