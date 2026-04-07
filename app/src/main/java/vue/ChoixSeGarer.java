package vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import controleur.ControleurSeGarer;

import javax.swing.*;
import java.awt.*;

import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import java.util.Arrays;

public class ChoixSeGarer extends RoundedPanel {
    private static final long serialVersionUID = 1L;
    private final App app;

    // Palette and fonts aligned with other pages
    private static final Color BG = new Color(22, 28, 38);
    private static final Color PANEL = new Color(28, 36, 52);
    private static final Color CARD = new Color(30, 38, 56);
    private static final Color ACCENT = new Color(100, 170, 255);
    private static final Color ACCENT_HOVER = new Color(85, 150, 235);
    private static final Color MUTED = new Color(170, 185, 205);

    private static final Font TITLE_FONT;
    private static final Font FIELD_FONT;

    static {
        String[] prefs = { "San Francisco", "Inter", "Roboto", "Segoe UI", "Noto Sans", "Dialog" };
        String chosen = Arrays.stream(prefs)
                .filter(n -> java.util.Arrays
                        .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                        .contains(n))
                .findFirst().orElse("Dialog");
        TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
        FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
    }

    public ChoixSeGarer(App app) {
        super(12, PANEL);
        this.app = app;
        FlatDarculaLaf.setup();
        setPreferredSize(new Dimension(1080, 720));
        initialize();
    }

    private void initialize() {
        ControleurSeGarer controler = new ControleurSeGarer(app);

        setLayout(new BorderLayout(0, 0));
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTitre = new JLabel("Choisissez où vous garer", SwingConstants.CENTER);
        labelTitre.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
        labelTitre.setForeground(Color.WHITE);
        labelTitre.setBorder(BorderFactory.createEmptyBorder(8, 0, 12, 0));
        add(labelTitre, BorderLayout.NORTH);

        RoundedPanel card = new RoundedPanel(12, CARD);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(24, 24, 24, 24);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JButton btnParking = styledButtonLarge("Parking", false);
        btnParking.addActionListener(controler);
        gbc.gridy = 0;
        center.add(btnParking, gbc);

        JButton btnVoirie = styledButtonLarge("Voirie", false);
        btnVoirie.addActionListener(controler);
        gbc.gridy = 1;
        center.add(btnVoirie, gbc);

        card.add(center, BorderLayout.CENTER);

        add(card, BorderLayout.CENTER);
    }

    private JButton styledButtonLarge(String text, boolean primary) {
        JButton b = new JButton(text);
        b.setFont(FIELD_FONT.deriveFont(Font.BOLD, 20f));
        b.setPreferredSize(new Dimension(380, 88));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primary) {
            b.setBackground(ACCENT);
            b.setForeground(Color.WHITE);
            b.setBorder(new RoundedBorder(new Color(0, 0, 0, 0), 12));
        } else {
            b.setBackground(new Color(28, 36, 52));
            b.setForeground(MUTED);
            b.setBorder(new RoundedBorder(new Color(34, 44, 66), 12, 1));
        }
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (primary)
                    b.setBackground(ACCENT_HOVER);
                else
                    b.setBackground(new Color(36, 46, 64));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (primary)
                    b.setBackground(ACCENT);
                else
                    b.setBackground(new Color(28, 36, 52));
            }
        });
        b.setBorder(BorderFactory.createCompoundBorder(b.getBorder(), BorderFactory.createEmptyBorder(12, 20, 12, 20)));
        return b;
    }
}