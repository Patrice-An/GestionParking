package vue;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import modele.Utilisateur;
import modele.Vehicule;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class GestionVehicule extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField serachField;
	private FenetrePrincipal fenetre;

	
	public GestionVehicule(FenetrePrincipal fenetre) {
		this.fenetre = fenetre;
		initialize();
	}
	/**
	 * Create the panel.
	 */
	public void initialize() {
		setLayout(new MigLayout("", "[45px,grow][][][][][][][][][][][][][][][]", "[13px][][][grow]"));
		
		JLabel TitreLabel = new JLabel("Gestion des véhicules");
		add(TitreLabel, "cell 0 0 16 1,alignx center,aligny top");
		
		serachField = new JTextField();
		add(serachField, "cell 0 2 4 1,growx");
		serachField.setColumns(10);
		
		JButton btnSearch = new JButton("Rechercher");
		add(btnSearch, "cell 4 2");
		
		for (Vehicule i:fenetre.getUtilisateur().getVehicules()) {
			JPanel carteInfosVehicule = new JPanel();
			carteInfosVehicule.setBorder(new LineBorder(new Color(0, 0, 0)));
			add(carteInfosVehicule, "cell 0 3 4 1,growx,aligny center");
			GridBagLayout gbl_carteInfosVehicule = new GridBagLayout();
			gbl_carteInfosVehicule.columnWidths = new int[]{0, 0};
			gbl_carteInfosVehicule.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_carteInfosVehicule.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_carteInfosVehicule.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			carteInfosVehicule.setLayout(gbl_carteInfosVehicule);
			
			JLabel Labeldesignation = new JLabel(i.getDésignation());
			GridBagConstraints gbc_Labeldesignation = new GridBagConstraints();
			gbc_Labeldesignation.insets = new Insets(0, 0, 5, 0);
			gbc_Labeldesignation.gridx = 0;
			gbc_Labeldesignation.gridy = 1;
			carteInfosVehicule.add(Labeldesignation, gbc_Labeldesignation);
			
			JLabel labelHauteur = new JLabel(Float.toString(i.getHauteur()));
			GridBagConstraints gbc_labelHauteur = new GridBagConstraints();
			gbc_labelHauteur.insets = new Insets(0, 0, 5, 0);
			gbc_labelHauteur.gridx = 0;
			gbc_labelHauteur.gridy = 3;
			carteInfosVehicule.add(labelHauteur, gbc_labelHauteur);
			
			JLabel labelImmat = new JLabel(i.getImmatriculation());
			GridBagConstraints gbc_labelImmat = new GridBagConstraints();
			gbc_labelImmat.gridx = 0;
			gbc_labelImmat.gridy = 4;
			carteInfosVehicule.add(labelImmat, gbc_labelImmat);
			
		}
		
		

	}

}
