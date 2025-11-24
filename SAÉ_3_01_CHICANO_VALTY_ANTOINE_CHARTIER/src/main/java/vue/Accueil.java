package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurAccueil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Accueil extends JFrame{
	
	private static final long serialVersionUID = 2L;

	public static void main(String[] args) {
		Accueil accueil = new Accueil();
	}
	
	public Accueil() {
		FlatDarculaLaf.setup();
			try {
				initialize();
				this.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void initialize() {
		ControleurAccueil controleur = new ControleurAccueil(this);
		this.setTitle("Accueil - Parkéo");
		this.setBounds(100, 100, 1080, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Charger le logo pour l'icône de la fenêtre depuis les ressources
		try {
			InputStream imgStream = getClass().getResourceAsStream("src/images/Parkeo_Logo.png");
			if (imgStream != null) {
				BufferedImage img = ImageIO.read(imgStream);
				this.setIconImage(img);
			} else {
				System.err.println("Erreur : Le fichier '/src/images/Parkeo_Logo.png' est introuvable dans les ressources.");
			}
		} catch (IOException e) {
			System.err.println("Erreur : Impossible de charger l'image.");
			e.printStackTrace();
		}

		// Panel principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);

		// Texte "Mode Simulation" centré en haut
		JLabel label = new JLabel("Mode Simulation", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 32));
		panel.add(label, BorderLayout.NORTH);

		// Panel pour les boutons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		// Bouton "Se Garer"
		JButton btnSeGarer = new JButton("Se Garer");
		btnSeGarer.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSeGarer.setPreferredSize(new Dimension(200, 60));
		buttonPanel.add(btnSeGarer);
		btnSeGarer.addActionListener(controleur);

		// Bouton "Partir"
		JButton btnPartir = new JButton("Partir");
		btnPartir.setFont(new Font("Arial", Font.PLAIN, 24));
		btnPartir.setPreferredSize(new Dimension(200, 60));
		buttonPanel.add(btnPartir);
		btnPartir.addActionListener(controleur);

		// Ajouter le panel des boutons au centre
		panel.add(buttonPanel, BorderLayout.CENTER);
	}

}
