package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurSeGarer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ChoixSeGarer extends JFrame{
	private JFrame frame;

	

	public ChoixSeGarer() {
		initialize();
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(() -> {
			try {
				
				this.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void initialize() {
		ControleurSeGarer controler = new ControleurSeGarer(this);
		frame = new JFrame("Choix de la zone - Parkéo");
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Charger le logo pour l'icône de la fenêtre depuis les ressources
		try {
			InputStream imgStream = getClass().getResourceAsStream("src/images/Parkeo_Logo.png");
			if (imgStream != null) {
				BufferedImage img = ImageIO.read(imgStream);
				frame.setIconImage(img);
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
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Texte "Mode Simulation" centré en haut
		JLabel label = new JLabel("Choisissez ou vous garer", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 32));
		panel.add(label, BorderLayout.NORTH);

		// Panel pour les boutons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

		// Bouton "Parkign"
		JButton btnSeGarer = new JButton("Parking");
		btnSeGarer.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSeGarer.setPreferredSize(new Dimension(200, 60));
		buttonPanel.add(btnSeGarer);
		btnSeGarer.addActionListener(controler);

		// Bouton "Voirie"
		JButton btnPartir = new JButton("Voirie");
		btnPartir.setFont(new Font("Arial", Font.PLAIN, 24));
		btnPartir.setPreferredSize(new Dimension(200, 60));
		buttonPanel.add(btnPartir);
		btnPartir.addActionListener(controler);

		// Ajouter le panel des boutons au centre
		panel.add(buttonPanel, BorderLayout.CENTER);
	}
}
