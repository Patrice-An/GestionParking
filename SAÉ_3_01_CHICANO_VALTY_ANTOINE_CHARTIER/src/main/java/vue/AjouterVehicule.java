package main.java.vue;

import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controleur.ControleurAjouterVehicule;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import modele.Type_vehicule;

public class AjouterVehicule extends JPanel{

	
	private JTextField txtDsignation;
	private JTextField ImmatriculationField;
	private FenetrePrincipal fenetrePrincipal;
	

	/**
	 * Create the application.
	 * @param fenetrePrincipal
	 */
	public AjouterVehicule(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		FlatDarculaLaf.setup();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleurAjouterVehicule controleur = new ControleurAjouterVehicule(this, fenetrePrincipal);
		
		JLabel enregistrerLabel = new JLabel("Enregister un nouveau véhicule");
		enregistrerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel PanneauCentre = new JPanel();
		PanneauCentre.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
		
		JLabel labelTitre = new JLabel("Enregistrer un nouveau véhicule");
		PanneauCentre.add(labelTitre, "1, 2, 10, 1, center, default");
		
		JLabel lblNewLabel = new JLabel("Hauteur :");
		PanneauCentre.add(lblNewLabel, "8, 6, left, bottom");
		
		txtDsignation = new JTextField();
		txtDsignation.setForeground(new Color(128, 128, 128));
		txtDsignation.setText("Désignation");
		PanneauCentre.add(txtDsignation, "2, 8, fill, default");
		txtDsignation.setColumns(10);
		txtDsignation.addActionListener(controleur);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Double.valueOf(0), null, null, Double.valueOf(0.1)));
		PanneauCentre.add(spinner, "8, 8, fill, default");
		
		JLabel lblNewLabel_1 = new JLabel("m");
		PanneauCentre.add(lblNewLabel_1, "10, 8");
		
		JLabel ImmatriculationLabel = new JLabel("Immatriculation :");
		PanneauCentre.add(ImmatriculationLabel, "2, 12, default, fill");
		
		JLabel typeVehiculeLabel = new JLabel("Type de véhicule :");
		PanneauCentre.add(typeVehiculeLabel, "8, 12");
		
		ImmatriculationField = new JTextField();
		ImmatriculationField.setForeground(new Color(128, 128, 128));
		ImmatriculationField.setText("AB-123-CD");
		PanneauCentre.add(ImmatriculationField, "2, 14, fill, top");
		ImmatriculationField.setColumns(10);
		ImmatriculationField.addActionListener(controleur);
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(Type_vehicule.values()));
		PanneauCentre.add(comboBoxType, "8, 14, fill, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		PanneauCentre.add(btnAnnuler, "2, 18, center, default");
		btnAnnuler.addActionListener(controleur);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		PanneauCentre.add(btnEnregistrer, "8, 18, center, center");
		
		this.add(PanneauCentre);
	}
}
