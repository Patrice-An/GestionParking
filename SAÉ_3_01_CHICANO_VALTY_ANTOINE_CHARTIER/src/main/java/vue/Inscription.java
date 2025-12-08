package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurInscription;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Inscription extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PanneauCentre;
	private JLabel title;
	private JLabel nomLabel;
	private JTextField nomField;
	private JLabel prenomLabel;
	private JTextField prenomField;
	private JLabel mailLabel;
	private JTextField mailField;
	private JLabel adresseLabel;
	private JTextField adresseField;
	private JLabel telLabel;
	private JTextField telField;
	private JPanel PanneauSud;
	private JButton btnValider;
	private JLabel lblDjInscrit;
	private JButton btnConnection;

	/**
	 * Launch the application.
	 */
	public Inscription(){
		FlatDarculaLaf.setup();
		try {
			this.initialize();
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};


/**
 * Create the frame.
 */
private void initialize() {
	ControleurInscription controleur = new ControleurInscription(this);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	setTitle("Inscription");
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));

	title = new JLabel("Inscription");
	title.setHorizontalAlignment(SwingConstants.CENTER);
	contentPane.add(title, BorderLayout.NORTH);

	PanneauCentre = new JPanel();
	contentPane.add(PanneauCentre, BorderLayout.CENTER);
	PanneauCentre.setLayout(new GridLayout(5, 2, 0, 0));

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

	adresseLabel = new JLabel("Adresse :");
	PanneauCentre.add(adresseLabel);

	adresseField = new JTextField();
	adresseField.setColumns(10);
	PanneauCentre.add(adresseField);

	telLabel = new JLabel("Numéro de téléphone :");
	PanneauCentre.add(telLabel);

	telField = new JTextField();
	telField.setColumns(10);
	PanneauCentre.add(telField);

	PanneauSud = new JPanel();
	contentPane.add(PanneauSud, BorderLayout.SOUTH);
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


public JTextField getAdresseField() {
	return adresseField;
}


public JTextField getTelField() {
	return telField;
}

}
