package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import modele.Type_vehicule;
import javax.swing.JButton;

public class ModifierVehicule extends JPanel {

	private static final long serialVersionUID = 1L;
	private FenetrePrincipal fenetrePrincipal;
	private JTextField txtDsignation;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ModifierVehicule(FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Modifier le véhicule");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		paneCenter.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Hauteur");
		paneCenter.add(lblNewLabel_1, "20, 6, 5, 1");
		
		txtDsignation = new JTextField();
		txtDsignation.setText("Désignation");
		paneCenter.add(txtDsignation, "4, 8, 5, 1, fill, default");
		txtDsignation.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		paneCenter.add(spinner, "20, 8");
		
		JLabel lblNewLabel_2 = new JLabel("m");
		paneCenter.add(lblNewLabel_2, "22, 8");
		
		JLabel lblNewLabel_3 = new JLabel("Immatriculation :");
		paneCenter.add(lblNewLabel_3, "4, 12, 5, 1");
		
		textField = new JTextField();
		paneCenter.add(textField, "4, 14, 5, 1, fill, default");
		textField.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(Type_vehicule.values()));
		paneCenter.add(comboBox_1, "20, 14, fill, default");
		
		JButton btnNewButton_1 = new JButton("Annuler");
		paneCenter.add(btnNewButton_1, "4, 20, 5, 1");
		
		JButton btnNewButton = new JButton("Enregistrer");
		paneCenter.add(btnNewButton, "20, 20");
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		
		JPanel paneWest = new JPanel();
		add(paneWest, BorderLayout.WEST);
		
		JPanel paneEast = new JPanel();
		add(paneEast, BorderLayout.EAST);

	}

}
