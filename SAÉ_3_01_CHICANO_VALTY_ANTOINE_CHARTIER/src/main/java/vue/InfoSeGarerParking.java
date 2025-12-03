package main.java.vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfosSeGarerParking;

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

public class InfoSeGarerParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SaisieHeureArrive;
	private JTextField SaisieDuree;
	private JTextField SaisieImmatriculation;
	JComboBox<String> DeroulantSelectionParking;
	JComboBox<Type_vehicule> DeroulantTypeV;
	
	/**
	 * Launch the application.
	 */
	public InfoSeGarerParking() {
				try {
					this.initialize();
					this.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			};

	/**
	 * Create the frame.
	 * @return 
	 */
	public void initialize() {
		ControleurInfosSeGarerParking controleur = new ControleurInfosSeGarerParking(this);
		
		setTitle("Se garer dans un parking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel PanneauNord = new JPanel();
		contentPane.add(PanneauNord, BorderLayout.NORTH);
		
		JPanel PanneauCentre = new JPanel();
		contentPane.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new BorderLayout(0, 0));
		
		JLabel TitreDonnees = new JLabel("Renseignez les données pour la simulation");
		TitreDonnees.setFont(new Font("Tahoma", Font.BOLD, 14));
		PanneauCentre.add(TitreDonnees, BorderLayout.NORTH);
		
		JPanel ListeInfos = new JPanel();
		PanneauCentre.add(ListeInfos, BorderLayout.CENTER);
		ListeInfos.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel LabelHeureArrive = new JLabel("Heure d'arrivée :");
		ListeInfos.add(LabelHeureArrive);
		
		SaisieHeureArrive = new JTextField();
		ListeInfos.add(SaisieHeureArrive);
		SaisieHeureArrive.setColumns(10);
		
		JLabel LabelHeureDepart = new JLabel("Heure de départ :");
		ListeInfos.add(LabelHeureDepart);
		
		SaisieDuree = new JTextField();
		ListeInfos.add(SaisieDuree);
		SaisieDuree.setColumns(10);
		
		JLabel LabelTypeVehicule = new JLabel("Type Véhicule :");
		ListeInfos.add(LabelTypeVehicule);
		
		DeroulantTypeV = new JComboBox<Type_vehicule>();
		DeroulantTypeV.setModel(new DefaultComboBoxModel<Type_vehicule>(Type_vehicule.values()));
		ListeInfos.add(DeroulantTypeV);
		
		JLabel LabelImmatriculation = new JLabel("Plaque Immatriculation :");
		ListeInfos.add(LabelImmatriculation);
		
		SaisieImmatriculation = new JTextField();
		ListeInfos.add(SaisieImmatriculation);
		SaisieImmatriculation.setColumns(10);
		
		JLabel LabelSelectionVoirie = new JLabel("Sélectionnez le Parking utilisé :");
		LabelSelectionVoirie.setFont(new Font("Tahoma", Font.BOLD, 14));
		ListeInfos.add(LabelSelectionVoirie);
		
		JLabel lblNewLabel = new JLabel(" ");
		ListeInfos.add(lblNewLabel);
		
		DeroulantSelectionParking = new JComboBox<String>();
		String[] options = {"JEANNE D'ARC", "CAPITOLE"};
		DeroulantSelectionParking.setModel(new DefaultComboBoxModel<String>(options));
		ListeInfos.add(DeroulantSelectionParking);
		
		JPanel PanneauSud = new JPanel();
		contentPane.add(PanneauSud, BorderLayout.SOUTH);
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

	public String getSaisieImmatriculation() {
		return SaisieImmatriculation.getText();
	}
	
	public String getParking(){
		return (String) DeroulantSelectionParking.getSelectedItem();
	}

	public String getDeroulantTypeV() {
		return DeroulantTypeV.getSelectedItem().toString();
	}
	
	
	
	
	
	
	

}
