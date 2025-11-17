package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.Font;

public class InfoSeGarerVoirie extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SaisieHeureArrive;
	private JTextField SaisieHeureDebut;
	private JTextField SaisieImmatriculation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoSeGarerVoirie frame = new InfoSeGarerVoirie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoSeGarerVoirie() {
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
		
		JLabel LabelDuree = new JLabel("Durée :");
		ListeInfos.add(LabelDuree);
		
		SaisieHeureDebut = new JTextField();
		ListeInfos.add(SaisieHeureDebut);
		SaisieHeureDebut.setColumns(10);
		
		JLabel LabelTypeVehicule = new JLabel("Type Véhicule :");
		ListeInfos.add(LabelTypeVehicule);
		
		JComboBox DeroulantTypeV = new JComboBox();
		ListeInfos.add(DeroulantTypeV);
		
		JLabel LabelImmatriculation = new JLabel("Plaque Immatriculation :");
		ListeInfos.add(LabelImmatriculation);
		
		SaisieImmatriculation = new JTextField();
		ListeInfos.add(SaisieImmatriculation);
		SaisieImmatriculation.setColumns(10);
		
		JLabel LabelSelectionParking = new JLabel("Sélectionnez le parking utilisé :");
		LabelSelectionParking.setFont(new Font("Tahoma", Font.BOLD, 14));
		ListeInfos.add(LabelSelectionParking);
		
		JLabel lblNewLabel = new JLabel(" ");
		ListeInfos.add(lblNewLabel);
		
		JComboBox DeroulantSlectionParking = new JComboBox();
		ListeInfos.add(DeroulantSlectionParking);
		
		JPanel PanneauSud = new JPanel();
		contentPane.add(PanneauSud, BorderLayout.SOUTH);
		PanneauSud.setLayout(new BorderLayout(0, 0));
		
		JButton btnValider = new JButton("Valider");
		btnValider.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnValider.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnValider.setVerticalAlignment(SwingConstants.BOTTOM);
		btnValider.setHorizontalAlignment(SwingConstants.RIGHT);
		PanneauSud.add(btnValider, BorderLayout.EAST);
		
		JButton btnAnnuler = new JButton("Annuler");
		PanneauSud.add(btnAnnuler, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		PanneauSud.add(panel, BorderLayout.NORTH);

	}

}
