package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfoSeGarer;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import modele.Parking;
import modele.Vehicule;
import modele.dao.DaoParking;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class InfoSeGarerParking extends JPanel {

	private static final long serialVersionUID = 1L;
	private LocalDateTime heureArrive;
	private JLabel labelHeureArrive;
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
		ControleurInfoSeGarer controleur = new ControleurInfoSeGarer(this, fenetrePrincipal);


		setBounds(100, 100, 450, 300);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(new BorderLayout(0, 0));

		JPanel PanneauNord = new JPanel();
		this.add(PanneauNord, BorderLayout.NORTH);

		JPanel PanneauCentre = new JPanel();
		this.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new BorderLayout(0, 0));

		JLabel TitreDonnees = new JLabel("Sélectionner un véhicule");
		TitreDonnees.setHorizontalAlignment(SwingConstants.CENTER);
		TitreDonnees.setFont(new Font("Tahoma", Font.BOLD, 21));
		PanneauCentre.add(TitreDonnees, BorderLayout.NORTH);

		JPanel ListeInfos = new JPanel();
		PanneauCentre.add(ListeInfos, BorderLayout.CENTER);
		ListeInfos.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("220px"),
				ColumnSpec.decode("220px:grow"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("125px"),
				RowSpec.decode("125px"),
				RowSpec.decode("125px"),
				RowSpec.decode("125px"),}));

		JLabel LabelHeureArrive = new JLabel("Heure d'arrivée :");
		ListeInfos.add(LabelHeureArrive, "1, 2, fill, fill");

		heureArrive = LocalDateTime.now();
		labelHeureArrive = new JLabel(heureArrive.getHour()+":"+heureArrive.getMinute());
		ListeInfos.add(labelHeureArrive, "2, 2, fill, fill");

		JLabel labelChoixVehicule = new JLabel("Choix véhicule :");
		ListeInfos.add(labelChoixVehicule, "1, 3, fill, fill");

		this.choixVehicule = new JComboBox<Vehicule>();
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<Vehicule>();
		for (Vehicule v : fenetrePrincipal.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);
		ListeInfos.add(choixVehicule, "2, 3, fill, fill");

		JLabel LabelSelectionParking = new JLabel("Sélectionnez le Parking utilisé :");
		LabelSelectionParking.setFont(new Font("Tahoma", Font.BOLD, 14));
		ListeInfos.add(LabelSelectionParking, "1, 4, fill, fill");

		JLabel lblNewLabel = new JLabel(" ");
		ListeInfos.add(lblNewLabel, "2, 5, fill, fill");

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
		ListeInfos.add(DeroulantSelectionParking, "1, 5, 2, 1, fill, fill");

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

	public LocalDateTime getHeureArrive() {
		return heureArrive;
	}

	public JComboBox<Vehicule> getChoixVehicule() {
		return choixVehicule;
	}

	public JComboBox<Parking> getDeroulantSelectionParking() {
		return DeroulantSelectionParking;
	}

}
