package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.JButton;

public class AjouterParking extends JPanel {

	private static final long serialVersionUID = 1L;

	private final App app;
	
	public AjouterParking(App app) {
		this.app = app;
		FlatDarculaLaf.setup();
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	private void initialize() {
		
		app.getUtilisateur();
		setBounds(100, 100, 436, 300);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new MigLayout("", "[grow][][][grow][grow][grow][grow]", "[][][][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel1 = new JLabel("Adresse :");
		this.add(lblNewLabel1, "cell 5 0");
		
		JTextField textField;
		JTextField textField1;
		textField = new JTextField();
		this.add(textField, "cell 0 1,alignx left");
		textField.setColumns(10);
		
		textField1 = new JTextField();
		this.add(textField1, "cell 5 1,growx");
		textField1.setColumns(10);
		
		JPanel panel = new JPanel();
		this.add(panel, "cell 0 3,growx,aligny top");
		
		JLabel lblNewLabel2 = new JLabel("Hauteur :");
		panel.add(lblNewLabel2);
		
		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(40, 20));
		panel.add(spinner);
		
		JPanel panel1 = new JPanel();
		this.add(panel1, "cell 5 3,grow");
		
		JLabel lblNewLabel21 = new JLabel("Nombre de places :");
		panel1.add(lblNewLabel21);
		
		JSpinner spinner1 = new JSpinner();
		spinner1.setPreferredSize(new Dimension(40, 20));
		panel1.add(spinner1);
		
		JPanel panel2 = new JPanel();
		this.add(panel2, "cell 0 4,grow");
		
		JLabel lblNewLabel22 = new JLabel("Nombre de place moto :");
		panel2.add(lblNewLabel22);
		
		JSpinner spinner2 = new JSpinner();
		spinner2.setPreferredSize(new Dimension(40, 20));
		panel2.add(spinner2);
		
		JPanel panel3 = new JPanel();
		this.add(panel3, "cell 5 4,grow");
		
		JLabel lblNewLabel23 = new JLabel("Nombre de place életrique :");
		panel3.add(lblNewLabel23);
		
		JSpinner spinner3 = new JSpinner();
		spinner3.setPreferredSize(new Dimension(40, 20));
		panel3.add(spinner3);
		
		JButton btnNewButton = new JButton("Annuler");
		this.add(btnNewButton, "cell 0 6,alignx left,aligny bottom");
		
		JButton btnNewButton1 = new JButton("Enregistrer");
		this.add(btnNewButton1, "cell 5 6,alignx right,aligny bottom");

	}

}
