package main.java.vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import controleur.ControleurSeGarer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ChoixSeGarer extends JPanel {

    private static final long serialVersionUID = 1L;
    private FenetrePrincipal fenetrePrincipal;

    public ChoixSeGarer(FenetrePrincipal fenetrePrincipal) {
    	this.fenetrePrincipal = fenetrePrincipal;
        FlatDarculaLaf.setup();
        initialize();
    }

    private void initialize() {
        ControleurSeGarer controler = new ControleurSeGarer(this, fenetrePrincipal);

       
        setSize(1080, 720);
        setLayout(new BorderLayout());


        // --- Bandeau supérieur ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitre = new JLabel("Choisissez où vous garer", SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 32));

        topPanel.add(labelTitre, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // --- Zone centrale (boutons) ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- Bouton Parking ---
        JButton btnParking = createBigButton("Parking");
        btnParking.addActionListener(controler);

        gbc.gridy = 0;
        centerPanel.add(btnParking, gbc);

        // --- Bouton Voirie ---
        JButton btnVoirie = createBigButton("Voirie");
        btnVoirie.addActionListener(controler);

        gbc.gridy = 1;
        centerPanel.add(btnVoirie, gbc);

        this.add(centerPanel, BorderLayout.CENTER);
        
    }

    /**
     * Bouton stylé homogène avec la fenêtre d’accueil.
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
