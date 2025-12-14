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

public class AjouterVehicule extends JPanel{

	private JTextField txtDésignation;
	private JTextField ImmatriculationField;
	private JSpinner hauteurField;
	private JComboBox<String> typeVehiculeComboBox;
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
		
		txtDésignation = new JTextField();
		txtDésignation.setForeground(new Color(128, 128, 128));
		txtDésignation.setText("Désignation");
		PanneauCentre.add(txtDésignation, "2, 8, fill, default");
		txtDésignation.setColumns(10);
		
		this.hauteurField = new JSpinner();
		this.hauteurField.setModel(new SpinnerNumberModel(Float.valueOf(0), null, null, Float.valueOf(0.1F)));
		PanneauCentre.add(this.hauteurField, "8, 8, fill, default");
		
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
		
		this.typeVehiculeComboBox = new JComboBox<String>();
		this.typeVehiculeComboBox.setModel(new DefaultComboBoxModel(Type_vehicule.values()));
		PanneauCentre.add(this.typeVehiculeComboBox, "8, 14, fill, top");
		
		JButton btnAnnuler = new JButton("Annuler");
		PanneauCentre.add(btnAnnuler, "2, 18, center, default");
		btnAnnuler.addActionListener(controleur);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		PanneauCentre.add(btnEnregistrer, "8, 18, center, center");
		btnEnregistrer.addActionListener(controleur);

		this.add(PanneauCentre);
	}

	public String getDésignation() {
		return txtDésignation.getText();
	}

	public String getImmatriculation() {
		return ImmatriculationField.getText();
	}

	public float getHauteur() {
		return (float) hauteurField.getValue();
	}

	public Type_vehicule getTypeVehicule() {
		return Type_vehicule.valueOf(typeVehiculeComboBox.getModel().getSelectedItem().toString());
	}

}
