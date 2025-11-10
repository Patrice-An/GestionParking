package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;
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
	private JLabel LabelVide;
	private JLabel LabelConnection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription frame = new Inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inscription() {
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
		PanneauSud.setLayout(new GridLayout(1, 3, 0, 0));
		
		LabelVide = new JLabel(" ");
		PanneauSud.add(LabelVide);
		
		LabelConnection = new JLabel("Déjà inscrit ? Connectez-vous ");
		PanneauSud.add(LabelConnection);
		
		btnValider = new JButton("Valider");
		PanneauSud.add(btnValider);
	}

}
