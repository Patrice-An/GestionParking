package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfoSeGarerVoirie;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import modele.Type_vehicule;
import modele.Zone;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InfoSeGarerVoirie extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField SaisieHeureDebut;
	private JTextField SaisieImmatriculation;
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
	ControleurInfoSeGarerVoirie controleur = new ControleurInfoSeGarerVoirie(this, fenetrePrincipal);
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

	JLabel LabelDuree = new JLabel("Durée :");
	ListeInfos.add(LabelDuree);

	SaisieHeureDebut = new JTextField();
	ListeInfos.add(SaisieHeureDebut);
	SaisieHeureDebut.setColumns(10);

	JLabel LabelTypeVehicule = new JLabel("Type Véhicule :");
	ListeInfos.add(LabelTypeVehicule);

	JComboBox DeroulantTypeV = new JComboBox();
	DeroulantTypeV.setModel(new DefaultComboBoxModel(Type_vehicule.values()));
	ListeInfos.add(DeroulantTypeV);

	JLabel LabelImmatriculation = new JLabel("Plaque Immatriculation :");
	ListeInfos.add(LabelImmatriculation);

	SaisieImmatriculation = new JTextField();
	ListeInfos.add(SaisieImmatriculation);
	SaisieImmatriculation.setColumns(10);

	JLabel LabelSelectionVoirie = new JLabel("Sélectionnez la zone utilisé :");
	LabelSelectionVoirie.setFont(new Font("Tahoma", Font.BOLD, 14));
	ListeInfos.add(LabelSelectionVoirie);

	JLabel lblNewLabel = new JLabel(" ");
	ListeInfos.add(lblNewLabel);

	JComboBox DeroulantSlectionParking = new JComboBox<String>();
	DeroulantSlectionParking.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent arg0) {
			System.out.println("éléments changé");
		}
	});
	DefaultComboBoxModel<String> voirieModel = new DefaultComboBoxModel<String>();
	for (Zone z : controleur.getZones()) {
		voirieModel.addElement(z.getCouleur());
	}
	DeroulantSlectionParking.setModel(voirieModel);
	ListeInfos.add(DeroulantSlectionParking);
	
	JComboBox comboBox = new JComboBox();
	ListeInfos.add(comboBox);

	JPanel PanneauSud = new JPanel();
	this.add(PanneauSud, BorderLayout.SOUTH);
	PanneauSud.setLayout(new BorderLayout(0, 0));

	JButton btnValider = new JButton("Valider");
	btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
	btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
	btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
	btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
	PanneauSud.add(btnValider, BorderLayout.EAST);

	JButton btnAnnuler = new JButton("Annuler");
	PanneauSud.add(btnAnnuler, BorderLayout.WEST);
	btnAnnuler.addActionListener(controleur);

	JPanel panel = new JPanel();
	PanneauSud.add(panel, BorderLayout.NORTH);

}

}
