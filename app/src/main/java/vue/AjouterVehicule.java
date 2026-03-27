package vue;

import javax.swing.JPanel;
import javax.swing.JSpinner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
import java.awt.Font;

public class AjouterVehicule extends JPanel{

	private JTextField txtDesignation;
	private JTextField immatriculationField;
	private JSpinner hauteurField;
	private JComboBox<Type_vehicule> typeVehiculeComboBox;
	private final App app;
	

	/**
	 * Create the application.
	 * @param App
	 */
	public AjouterVehicule(App app) {
		this.app = app;
		FlatDarculaLaf.setup();
		initialize();
	}

	/********************************************
	 * Initialize the contents of the frame.	*
	 ********************************************/
	private void initialize() {
		ControleurAjouterVehicule controleur = new ControleurAjouterVehicule(this, app);
		
		JLabel enregistrerLabel = new JLabel("Enregister un nouveau véhicule");
		enregistrerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		String defaultGrow = "default:grow";
		
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode(defaultGrow),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode(defaultGrow),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode(defaultGrow),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode(defaultGrow),
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
				RowSpec.decode(defaultGrow),}));
		
		JLabel labelTitre = new JLabel("Enregistrer un nouveau véhicule");
		labelTitre.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		panneauCentre.add(labelTitre, "1, 2, 10, 1, center, default");
		
		JLabel lblNewLabel = new JLabel("Hauteur :");
		panneauCentre.add(lblNewLabel, "8, 6, left, bottom");
		
		txtDesignation = new JTextField();
		txtDesignation.setForeground(new Color(128, 128, 128));
		txtDesignation.setText("Désignation");
		panneauCentre.add(txtDesignation, "2, 8, fill, default");
		txtDesignation.setColumns(10);
		
		this.hauteurField = new JSpinner();
		this.hauteurField.setModel(new SpinnerNumberModel(Float.valueOf(0), null, null, 0.1F));
		panneauCentre.add(this.hauteurField, "8, 8, fill, default");
		
		JLabel lblNewLabel11 = new JLabel("m");
		panneauCentre.add(lblNewLabel11, "10, 8");
		
		JLabel immatriculationLabel = new JLabel("Immatriculation :");
		panneauCentre.add(immatriculationLabel, "2, 12, default, fill");
		
		JLabel typeVehiculeLabel = new JLabel("Type de véhicule :");
		panneauCentre.add(typeVehiculeLabel, "8, 12");
		
		immatriculationField = new JTextField();
		immatriculationField.setForeground(new Color(128, 128, 128));
		immatriculationField.setText("AB-123-CD");
		panneauCentre.add(immatriculationField, "2, 14, fill, top");
		immatriculationField.setColumns(10);
		immatriculationField.addActionListener(controleur);
		
		this.typeVehiculeComboBox = new JComboBox<>();
		DefaultComboBoxModel<Type_vehicule> typeVehiculeModel = new DefaultComboBoxModel<>(Type_vehicule.values());
		this.typeVehiculeComboBox.setModel(typeVehiculeModel);
		panneauCentre.add(this.typeVehiculeComboBox, "8, 14, fill, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		panneauCentre.add(btnAnnuler, "2, 18, center, default");
		btnAnnuler.addActionListener(controleur);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		panneauCentre.add(btnEnregistrer, "8, 18, center, center");
		btnEnregistrer.addActionListener(controleur);

		this.add(panneauCentre);
	}

	public String getDesignation() {
		return txtDesignation.getText();
	}

	public String getImmatriculation() {
		return immatriculationField.getText();
	}

	public float getHauteur() {
		return (float) hauteurField.getValue();
	}

	public Type_vehicule getTypeVehicule() {
		return (Type_vehicule) typeVehiculeComboBox.getModel().getSelectedItem();
	}

}
