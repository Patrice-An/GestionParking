package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controleur.ControleurAjouterVehicule;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import modele.Type_vehicule;

public class AjouterVehicule extends JFrame{

	private JFrame frame;
	private JTextField txtDsignation;
	private JTextField ImmatriculationField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterVehicule window = new AjouterVehicule();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AjouterVehicule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleurAjouterVehicule controleur = new ControleurAjouterVehicule(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel enregistrerLabel = new JLabel("Enregister un nouveau véhicule");
		enregistrerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(enregistrerLabel, BorderLayout.NORTH);
		
		JPanel PanneauCentre = new JPanel();
		frame.getContentPane().add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		txtDsignation = new JTextField();
		txtDsignation.setForeground(new Color(128, 128, 128));
		txtDsignation.setText("Désignation");
		PanneauCentre.add(txtDsignation, "2, 6, fill, default");
		txtDsignation.setColumns(10);
		txtDsignation.addActionListener(controleur);
		
		JLabel ImmatriculationLabel = new JLabel("Immatriculation :");
		PanneauCentre.add(ImmatriculationLabel, "2, 10, default, fill");
		
		JLabel typeVehiculeLabel = new JLabel("Type de véhicule :");
		PanneauCentre.add(typeVehiculeLabel, "8, 10");
		
		ImmatriculationField = new JTextField();
		ImmatriculationField.setForeground(new Color(128, 128, 128));
		ImmatriculationField.setText("AB-123-CD");
		PanneauCentre.add(ImmatriculationField, "2, 12, fill, top");
		ImmatriculationField.setColumns(10);
		ImmatriculationField.addActionListener(controleur);
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(Type_vehicule.values()));
		PanneauCentre.add(comboBoxType, "8, 12, fill, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		PanneauCentre.add(btnAnnuler, "2, 16, center, default");
		btnAnnuler.addActionListener(controleur);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		PanneauCentre.add(btnEnregistrer, "8, 16, center, default");
	}
}
