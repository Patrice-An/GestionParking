package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfosSeGarerParking;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import modele.Parking;
import modele.Vehicule;
import modele.dao.DaoParking;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InfoSeGarerParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private LocalDateTime heureArrivée;
	private JLabel LabelHeureArrivée;
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
										GridBagLayout gridBagLayout = new GridBagLayout();
										gridBagLayout.columnWidths = new int[]{221, 220, 0};
										gridBagLayout.rowHeights = new int[]{33, 33, 33, 38, 0};
										gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
										gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
										ListeInfos.setLayout(gridBagLayout);
																
																		JLabel LabelHeureArrive = new JLabel("Heure d'arrivée :");
																		GridBagConstraints gbc_LabelHeureArrive = new GridBagConstraints();
																		gbc_LabelHeureArrive.fill = GridBagConstraints.BOTH;
																		gbc_LabelHeureArrive.insets = new Insets(0, 0, 5, 5);
																		gbc_LabelHeureArrive.gridx = 0;
																		gbc_LabelHeureArrive.gridy = 0;
																		ListeInfos.add(LabelHeureArrive, gbc_LabelHeureArrive);
																this.LabelHeureArrivée = new JLabel(String.valueOf(this.heureArrivée.getHour()) + "H" + String.valueOf(this.heureArrivée.getMinute()));
																GridBagConstraints gbc_labelHeureArrivée = new GridBagConstraints();
																gbc_labelHeureArrivée.fill = GridBagConstraints.BOTH;
																gbc_labelHeureArrivée.insets = new Insets(0, 0, 5, 0);
																gbc_labelHeureArrivée.gridx = 1;
																gbc_labelHeureArrivée.gridy = 0;
																ListeInfos.add(LabelHeureArrivée, gbc_labelHeureArrivée);
														
																JLabel LabelChoixVehicule = new JLabel("Choix véhicule :");
																GridBagConstraints gbc_LabelChoixVehicule = new GridBagConstraints();
																gbc_LabelChoixVehicule.fill = GridBagConstraints.BOTH;
																gbc_LabelChoixVehicule.insets = new Insets(0, 0, 5, 5);
																gbc_LabelChoixVehicule.gridx = 0;
																gbc_LabelChoixVehicule.gridy = 1;
																ListeInfos.add(LabelChoixVehicule, gbc_LabelChoixVehicule);
												
														this.choixVehicule = new JComboBox<Vehicule>();
														this.choixVehicule.setModel(vehiculeModel);
														GridBagConstraints gbc_choixVehicule = new GridBagConstraints();
														gbc_choixVehicule.fill = GridBagConstraints.BOTH;
														gbc_choixVehicule.insets = new Insets(0, 0, 5, 0);
														gbc_choixVehicule.gridx = 1;
														gbc_choixVehicule.gridy = 1;
														ListeInfos.add(choixVehicule, gbc_choixVehicule);
														
																JLabel LabelSelectionParking = new JLabel("Sélectionnez le Parking utilisé :");
																LabelSelectionParking.setFont(new Font("Tahoma", Font.BOLD, 14));
																GridBagConstraints gbc_LabelSelectionParking = new GridBagConstraints();
																gbc_LabelSelectionParking.fill = GridBagConstraints.BOTH;
																gbc_LabelSelectionParking.insets = new Insets(0, 0, 5, 5);
																gbc_LabelSelectionParking.gridx = 0;
																gbc_LabelSelectionParking.gridy = 2;
																ListeInfos.add(LabelSelectionParking, gbc_LabelSelectionParking);
												
														JLabel lblNewLabel = new JLabel(" ");
														GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
														gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
														gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
														gbc_lblNewLabel.gridx = 1;
														gbc_lblNewLabel.gridy = 2;
														ListeInfos.add(lblNewLabel, gbc_lblNewLabel);
										
												this.DeroulantSelectionParking = new JComboBox<Parking>();
												DeroulantSelectionParking.setModel(parkingModel);
												GridBagConstraints gbc_deroulantSelectionParking = new GridBagConstraints();
												gbc_deroulantSelectionParking.gridwidth = 2;
												gbc_deroulantSelectionParking.fill = GridBagConstraints.BOTH;
												gbc_deroulantSelectionParking.insets = new Insets(0, 0, 0, 5);
												gbc_deroulantSelectionParking.gridx = 0;
												gbc_deroulantSelectionParking.gridy = 3;
												ListeInfos.add(DeroulantSelectionParking, gbc_deroulantSelectionParking);

		this.heureArrivée = LocalDateTime.now();
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<Vehicule>();
		for (Vehicule v : fenetrePrincipal.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		DefaultComboBoxModel<Parking> parkingModel = new DefaultComboBoxModel<Parking>();
		try {
			for (Parking p : new DaoParking().findAll()) {
				parkingModel.addElement(p);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

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
		return this.LabelHeureArrivée.getText();
	}

	public JComboBox<Vehicule> getSaisieImmatriculation() {
		return this.choixVehicule;
	}

	public String getParking(){
		return (String) DeroulantSelectionParking.getSelectedItem();
	}
}
