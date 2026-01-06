package vue;

import controleur.ControleurInscription;

import javax.swing.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class Inscription extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel PanneauCentre;
	private JLabel title;
	private JLabel nomLabel;
	private JTextField nomField;
	private JLabel prenomLabel;
	private JTextField prenomField;
	private JLabel mailLabel;
	private JTextField mailField;
	private JLabel naissanceLabel;
	private JTextField naissanceField;
	private JLabel telLabel;
	private JTextField telField;
	private JPanel PanneauSud;
	private JButton btnValider;
	private JButton btnConnection;
	private JLabel pwdLabel;
	private JPasswordField passwordField;
	private JPanel panel;
	private JPanel panel_1;
	private FenetrePrincipal fenetrePrincipal;
	private JLabel label;

	/**
	 * Launch the application.
	 * @param fenetrePrincipal 
	 */
	public Inscription(FenetrePrincipal fenetrePrincipal){
			
			this.fenetrePrincipal = fenetrePrincipal;
			this.initialize();
	};


	/**
	 * Create the frame.
	 */
	private void initialize() {
		ControleurInscription controleur = new ControleurInscription(this, fenetrePrincipal);
		setBounds(100, 100, 450, 300);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		this.setLayout(new BorderLayout(0, 0));

		title = new JLabel("Inscription");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);

		PanneauCentre = new JPanel();
		this.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new MigLayout("", "[210px][][210px]", "[42px][42px][42px][42px][42px][42px]"));

		nomLabel = new JLabel("Nom :");
		PanneauCentre.add(nomLabel, "cell 0 0,alignx left,growy");
		
				nomField = new JTextField();
				PanneauCentre.add(nomField, "cell 2 0,growx,aligny center");
				nomField.setColumns(10);

		prenomLabel = new JLabel("Prenom :");
		PanneauCentre.add(prenomLabel, "cell 0 1,alignx left,growy");
		
				prenomField = new JTextField();
				prenomField.setColumns(10);
				PanneauCentre.add(prenomField, "cell 2 1,growx,aligny center");

		mailLabel = new JLabel("Mail :");
		PanneauCentre.add(mailLabel, "cell 0 2,alignx left,growy");
		
				mailField = new JTextField();
				mailField.setColumns(10);
				PanneauCentre.add(mailField, "cell 2 2,growx,aligny center");

		naissanceLabel = new JLabel("Naissance :");
		PanneauCentre.add(naissanceLabel, "cell 0 3,alignx left,growy");
		
				naissanceField = new JTextField();
				naissanceField.setColumns(10);
				PanneauCentre.add(naissanceField, "cell 2 3,growx,aligny center");

		telLabel = new JLabel("Numéro de téléphone :");
		PanneauCentre.add(telLabel, "cell 0 4,alignx left,growy");
		
			telField = new JTextField();
			telField.setColumns(10);
			PanneauCentre.add(telField, "cell 2 4,growx,aligny center");
		
		pwdLabel = new JLabel("Mot de passe :");
		PanneauCentre.add(pwdLabel, "cell 0 5,alignx left,growy");
		
		passwordField = new JPasswordField();
		PanneauCentre.add(passwordField, "cell 2 5,growx,aligny center");

		PanneauSud = new JPanel();
		this.add(PanneauSud, BorderLayout.SOUTH);
		PanneauSud.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label = new JLabel("");
		PanneauSud.add(label);

		btnConnection = new JButton("Connectez-vous");
		btnConnection.setPreferredSize(new Dimension(200, 50));
		PanneauSud.add(btnConnection);
		btnConnection.addActionListener(controleur);


		btnValider = new JButton("Valider");
		btnValider.setPreferredSize(new Dimension(200, 50));
		PanneauSud.add(btnValider);
		
		panel = new JPanel();
		this.add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		this.add(panel_1, BorderLayout.EAST);
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


	public JTextField getNaissanceField() {
		return naissanceField;
	}

	public JTextField getTelField() {
		return telField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

}
