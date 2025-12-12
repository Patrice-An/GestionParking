package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import controleur.ControleurAccueil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Accueil extends JPanel {

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

        // --- Contrôleur ---
        ControleurAccueil controleur = new ControleurAccueil(this);

        setSize(1080, 720);
        setLayout(new BorderLayout());

      
        // --- Bandeau supérieur ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitre = new JLabel("Mode Simulation : Bienvenue " + user, SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 32));

        topPanel.add(labelTitre, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // --- Panel central : contient les boutons ---
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

        // ----- BOUTON : Partir -----
        JButton btnPartir = createBigButton("Partir");
        btnPartir.addActionListener(controleur);

        gbc.gridy = 1;
        centerPanel.add(btnPartir, gbc);

        // ----- NOUVEAUX BOUTONS POSSIBLES -----
        JButton btnHistorique = createBigButton("Historique des Stationnements");
        btnHistorique.addActionListener(controleur);

        gbc.gridy = 2;
        centerPanel.add(btnHistorique, gbc);

        JButton btnGestionVehicule = createBigButton("Ajout Véhicule");
        btnGestionVehicule.addActionListener(controleur);

        gbc.gridy = 3;
        centerPanel.add(btnGestionVehicule, gbc);

        // Ajouter le panneau central
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Crée un bouton stylé cohérent pour l’accueil.
     */
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
