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
	private JComboBox<Vehicule> choixVehicule;
	private JComboBox<Parking> deroulantSelectionParking;
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

		JPanel panneauNord = new JPanel();
		this.add(panneauNord, BorderLayout.NORTH);

		JPanel panneauCentre = new JPanel();
		this.add(panneauCentre, BorderLayout.CENTER);
		panneauCentre.setLayout(new BorderLayout(0, 0));

		JLabel titreDonnees = new JLabel("Sélectionner un véhicule");
		titreDonnees.setHorizontalAlignment(SwingConstants.CENTER);
		titreDonnees.setFont(new Font("Tahoma", Font.BOLD, 21));
		panneauCentre.add(titreDonnees, BorderLayout.NORTH);

		JPanel listeInfos = new JPanel();
		panneauCentre.add(listeInfos, BorderLayout.CENTER);

		final String rowSpecSize = "125px";

		listeInfos.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("220px"),
				ColumnSpec.decode("220px:grow"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode(rowSpecSize),
				RowSpec.decode(rowSpecSize),
				RowSpec.decode(rowSpecSize),
				RowSpec.decode(rowSpecSize),}));

		JLabel labelHeureArrive = new JLabel("Heure d'arrivée :");
		listeInfos.add(labelHeureArrive, "1, 2, fill, fill");

		heureArrive = LocalDateTime.now();
		labelHeureArrive = new JLabel(heureArrive.getHour()+":"+heureArrive.getMinute());
		listeInfos.add(labelHeureArrive, "2, 2, fill, fill");

		JLabel labelChoixVehicule = new JLabel("Choix véhicule :");
		listeInfos.add(labelChoixVehicule, "1, 3, fill, fill");

		this.choixVehicule = new JComboBox<>();
		build();
		
		listeInfos.add(choixVehicule, "2, 3, fill, fill");

		JLabel labelSelectionParking = new JLabel("Sélectionnez le Parking utilisé :");
		labelSelectionParking.setFont(new Font("Tahoma", Font.BOLD, 14));
		listeInfos.add(labelSelectionParking, "1, 4, fill, fill");

		JLabel lblNewLabel = new JLabel(" ");
		listeInfos.add(lblNewLabel, "2, 5, fill, fill");

		deroulantSelectionParking = new JComboBox<>();
		DefaultComboBoxModel<Parking> parkingModel = new DefaultComboBoxModel<>();
		try {
			for (Parking p : new DaoParking().findAll()) {
				parkingModel.addElement(p);
			}
		} catch (SQLException _) {
			//empty
		}
		deroulantSelectionParking.setModel(parkingModel);
		listeInfos.add(deroulantSelectionParking, "1, 5, 2, 1, fill, fill");

		JPanel panneauSud = new JPanel();
		this.add(panneauSud, BorderLayout.SOUTH);
		panneauSud.setLayout(new BorderLayout(0, 0));

		JButton btnValider = new JButton("Valider");
		btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
		btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
		panneauSud.add(btnValider, BorderLayout.EAST);
		btnValider.addActionListener(controleur);

		JPanel panel = new JPanel();
		panneauSud.add(panel, BorderLayout.NORTH);

		JButton btnAnnuler = new JButton("Annuler");
		panneauSud.add(btnAnnuler, BorderLayout.WEST);
		btnAnnuler.addActionListener(controleur);

	}

	public LocalDateTime getHeureArrive() {
		return heureArrive;
	}

	public JComboBox<Vehicule> getChoixVehicule() {
		return choixVehicule;
	}

	public JComboBox<Parking> getDeroulantSelectionParking() {
		return deroulantSelectionParking;
	}
	
	private void build() {
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<>();
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
