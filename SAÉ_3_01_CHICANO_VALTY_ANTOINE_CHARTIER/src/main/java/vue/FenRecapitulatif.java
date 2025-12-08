package main.java.vue;

import javax.swing.*;
import java.awt.*;

public class FenRecapitulatif extends JFrame {

    private static final long serialVersionUID = 1L;

    public FenRecapitulatif(String heureArrive, String heureDepart, String plaqueImma, String parking) {
        try {
            initialize(heureArrive, heureDepart, plaqueImma, parking);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize(String heureArrive, String heureDepart, String plaqueImma, String parking) {

        setTitle("Récapitulatif stationnement");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Panel Titre ---
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel titre = new JLabel("VOUS ÊTES STATIONNÉ !", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 26));
        panelTop.add(titre, BorderLayout.CENTER);

        add(panelTop, BorderLayout.NORTH);

        // --- Panel Central : tableau d'informations ---
        JPanel panelCenter = new JPanel(new GridBagLayout());
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ligne A
        gbc.gridx = 0; gbc.gridy = 0;
        panelCenter.add(createLabel("Heure d'arrivée : "), gbc);
        gbc.gridx = 1;
        panelCenter.add(createDataLabel(heureArrive), gbc);

        // Ligne B
        gbc.gridx = 0; gbc.gridy = 1;
        panelCenter.add(createLabel("Heure de départ : "), gbc);
        gbc.gridx = 1;
        panelCenter.add(createDataLabel(heureDepart), gbc);

        // Ligne C
        gbc.gridx = 0; gbc.gridy = 2;
        panelCenter.add(createLabel("Immatriculation : "), gbc);
        gbc.gridx = 1;
        panelCenter.add(createDataLabel(plaqueImma), gbc);

        // Ligne D
        gbc.gridx = 0; gbc.gridy = 3;
        panelCenter.add(createLabel("Parking choisi : "), gbc);
        gbc.gridx = 1;
        panelCenter.add(createDataLabel(parking), gbc);

        add(panelCenter, BorderLayout.CENTER);

        // --- Panel Bas : bouton Confirmer ---
        JPanel panelBottom = new JPanel();
        panelBottom.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));

        JButton btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setFont(new Font("Arial", Font.PLAIN, 20));
        btnConfirmer.setPreferredSize(new Dimension(200, 50));

        panelBottom.add(btnConfirmer);
        add(panelBottom, BorderLayout.SOUTH);
    }

    /** Label descriptif */
    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        return lbl;
    }

    /** Label données utilisateur */
    private JLabel createDataLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        return lbl;
    }
}
