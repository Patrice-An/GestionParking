package main.java.vue;

import controleur.ControleurInscription;

import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	private JLabel lblDjInscrit;
	private JButton btnConnection;
	private JLabel pwdLabel;
	private JPasswordField passwordField;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public Inscription(){
			this.initialize();
	};


	/**
	 * Create the frame.
	 */
	private void initialize() {
		ControleurInscription controleur = new ControleurInscription(this);
		setBounds(100, 100, 450, 300);
		
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		this.setLayout(new BorderLayout(0, 0));

		title = new JLabel("Inscription");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);

		PanneauCentre = new JPanel();
		this.add(PanneauCentre, BorderLayout.CENTER);
		PanneauCentre.setLayout(new GridLayout(6, 2, 0, 0));

		nomLabel = new JLabel("Nom :");
		PanneauCentre.add(nomLabel);

		nomField = new JTextField();
		PanneauCentre.add(nomField);
		nomField.setColumns(10);

		prenomLabel = new JLabel("Prenom :");
		PanneauCentre.add(prenomLabel);

		prenomField = new JTextField();
		prenomField.setColumns(10);
		PanneauCentre.add(prenomField);

		mailLabel = new JLabel("Mail :");
		PanneauCentre.add(mailLabel);

		mailField = new JTextField();
		mailField.setColumns(10);
		PanneauCentre.add(mailField);

		naissanceLabel = new JLabel("Naissance :");
		PanneauCentre.add(naissanceLabel);

		naissanceField = new JTextField();
		naissanceField.setColumns(10);
		PanneauCentre.add(naissanceField);

		telLabel = new JLabel("Numéro de téléphone :");
		PanneauCentre.add(telLabel);
		
			telField = new JTextField();
			telField.setColumns(10);
			PanneauCentre.add(telField);
		
		pwdLabel = new JLabel("Mot de passe :");
		PanneauCentre.add(pwdLabel);
		
		passwordField = new JPasswordField();
		PanneauCentre.add(passwordField);

		PanneauSud = new JPanel();
		this.add(PanneauSud, BorderLayout.SOUTH);
		GridBagLayout gbl_PanneauSud = new GridBagLayout();
		gbl_PanneauSud.columnWidths = new int[]{141, 141, 141, 0};
		gbl_PanneauSud.rowHeights = new int[]{23, 0};
		gbl_PanneauSud.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_PanneauSud.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		PanneauSud.setLayout(gbl_PanneauSud);

		lblDjInscrit = new JLabel("Déjà inscrit ?");
		GridBagConstraints gbc_lblDjInscrit = new GridBagConstraints();
		gbc_lblDjInscrit.insets = new Insets(0, 0, 0, 5);
		gbc_lblDjInscrit.gridx = 0;
		gbc_lblDjInscrit.gridy = 0;
		PanneauSud.add(lblDjInscrit, gbc_lblDjInscrit);

		btnConnection = new JButton("Connectez-vous");
		GridBagConstraints gbc_btnConnection = new GridBagConstraints();
		gbc_btnConnection.fill = GridBagConstraints.BOTH;
		gbc_btnConnection.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnection.gridx = 1;
		gbc_btnConnection.gridy = 0;
		PanneauSud.add(btnConnection, gbc_btnConnection);
		btnConnection.addActionListener(controleur);


		btnValider = new JButton("Valider");
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.fill = GridBagConstraints.BOTH;
		gbc_btnValider.gridx = 2;
		gbc_btnValider.gridy = 0;
		PanneauSud.add(btnValider, gbc_btnValider);
		
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
