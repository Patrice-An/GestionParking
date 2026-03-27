package vue;

import controleur.ControleurInscription;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Inscription extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField mailField;
	private JDatePickerImpl naissancePicker;
	private JTextField telField;
	private JPasswordField passwordField;
	private App app;

	/**
	 * Launch the application.
	 * @param App 
	 */
	public Inscription(App app){
			this.app = app;
			this.initialize();
	}


	/**
	 * Create the frame.
	 */
	private void initialize() {
		ControleurInscription controleur = new ControleurInscription(this, app);
		setBounds(100, 100, 450, 300);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		this.setLayout(new BorderLayout(0, 0));

		JLabel title = new JLabel("Inscription");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);

		JPanel panneauCentre = new JPanel();
		this.add(panneauCentre, BorderLayout.CENTER);
		panneauCentre.setLayout(new MigLayout("", "[210px][][210px]", "[42px][42px][42px][42px][42px][42px]"));

		JLabel nomLabel = new JLabel("Nom :");
		panneauCentre.add(nomLabel, "cell 0 0,alignx left,growy");
		
				nomField = new JTextField();
				panneauCentre.add(nomField, "cell 2 0,growx,aligny center");
				nomField.setColumns(10);

		JLabel prenomLabel = new JLabel("Prenom :");
		panneauCentre.add(prenomLabel, "cell 0 1,alignx left,growy");
		
				prenomField = new JTextField();
				prenomField.setColumns(10);
				panneauCentre.add(prenomField, "cell 2 1,growx,aligny center");

		JLabel mailLabel = new JLabel("Mail :");
		panneauCentre.add(mailLabel, "cell 0 2,alignx left,growy");
		
				mailField = new JTextField();
				mailField.setColumns(10);
				panneauCentre.add(mailField, "cell 2 2,growx,aligny center");

		UtilDateModel modelDate = new UtilDateModel();
		java.util.Properties p = new java.util.Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, p);
		naissancePicker = new JDatePickerImpl(datePanel, null);
		modelDate.setSelected(true);
		
		JLabel naissanceLabel = new JLabel("Date naissance :");
		panneauCentre.add(naissanceLabel, "cell 0 3");

		panneauCentre.add(naissancePicker, "cell 2 3");
		

		JLabel telLabel = new JLabel("Numéro de téléphone :");
		panneauCentre.add(telLabel, "cell 0 4,alignx left,growy");
		
			telField = new JTextField();
			telField.setColumns(10);
			panneauCentre.add(telField, "cell 2 4,growx,aligny center");
		
		JLabel pwdLabel = new JLabel("Mot de passe :");
		panneauCentre.add(pwdLabel, "cell 0 5,alignx left,growy");
		
		passwordField = new JPasswordField();
		panneauCentre.add(passwordField, "cell 2 5,growx,aligny center");

		JPanel panneauSud = new JPanel();
		this.add(panneauSud, BorderLayout.SOUTH);
		panneauSud.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnConnection = new JButton("Connectez-vous");
		btnConnection.setPreferredSize(new Dimension(200, 50));
		panneauSud.add(btnConnection);
		btnConnection.addActionListener(controleur);


		JButton btnValider = new JButton("Valider");
		btnValider.setPreferredSize(new Dimension(200, 50));
		panneauSud.add(btnValider);
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.WEST);
		
		JPanel panel1 = new JPanel();
		this.add(panel1, BorderLayout.EAST);
		btnValider.addActionListener(controleur);
	}

	public JTextField getNomField() {
		return nomField;
	}

	public JTextField getPrenomField() {
		return prenomField;
	}

	public JTextField getMailField() {
		return mailField;
	}

	public JDatePickerImpl getNaissancePicker() {
		return naissancePicker;
	}

	public JTextField getTelField() {
		return telField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

}
