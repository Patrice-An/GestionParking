package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import controleur.ControleurAccueil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Accueil extends JFrame {

    private static final long serialVersionUID = 2L;

    public Accueil(String user) {
        FlatDarculaLaf.setup();
        try {
            initialize(user);
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize(String user) {

        ControleurAccueil controleur = new ControleurAccueil(this);

        setTitle("Accueil - Parkéo");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Chargement du logo corrigé ---
        try (InputStream imgStream = getClass().getResourceAsStream("/images/Parkeo_Logo.png")) {
            if (imgStream != null) {
                BufferedImage img = ImageIO.read(imgStream);
                setIconImage(img);
            } else {
                System.err.println("Erreur : Ressource /images/Parkeo_Logo.png introuvable.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Bandeau supérieur ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitre = new JLabel("Mode Simulation : Bienvenue " + user, SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 32));

        topPanel.add(labelTitre, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // --- Panel central ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // ----- BOUTON : Se Garer -----
        JButton btnSeGarer = createBigButton("Se Garer");
        btnSeGarer.addActionListener(controleur);

        gbc.gridy = 0;
        centerPanel.add(btnSeGarer, gbc);

        // ----- BOUTON : Quitter l'application -----
        JButton btnQuitter = createBigButton("Quitter l'application");
        btnQuitter.addActionListener(controleur);

        gbc.gridy = 1;
        centerPanel.add(btnQuitter, gbc);

       

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createBigButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setPreferredSize(new Dimension(350, 80));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }
}
