package vue;

import javax.swing.*;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

import controleur.ControleurAbonnement;
import modele.Abonnement;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class GestionAbonnement extends RoundedPanel {
    private static final long serialVersionUID = 1L;
    private final App app;
    private final transient ControleurAbonnement controleur;

    // Palette and fonts (same as other pages)
    private static final Color BG = new Color(22, 28, 38);
    private static final Color PANEL = new Color(28, 36, 52);
    private static final Color CARD = new Color(30, 38, 56);
    private static final Color ACCENT = new Color(100, 170, 255);
    private static final Color ACCENT_HOVER = new Color(85, 150, 235);
    private static final Color MUTED = new Color(170, 185, 205);
    private static final Color FIELD_BG = new Color(26, 34, 46);
    private static final Color FIELD_BORDER = new Color(56, 72, 104);

    private static final Font TITLE_FONT;
    private static final Font LABEL_FONT;
    private static final Font FIELD_FONT;

    static {
        String[] prefs = { "San Francisco", "Inter", "Roboto", "Segoe UI", "Noto Sans", "Dialog" };
        String chosen = java.util.Arrays.stream(prefs)
                .filter(n -> java.util.Arrays
                        .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                        .contains(n))
                .findFirst().orElse("Dialog");
        TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
        LABEL_FONT = new Font(chosen, Font.PLAIN, 13);
        FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
    }

    public GestionAbonnement(App app) {
        super(12, PANEL);
        this.app = app;
        this.controleur = new ControleurAbonnement(this.app);
        setPreferredSize(new Dimension(500, 720));
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(0, 0));
        setBackground(BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Gestion abonnement", SwingConstants.CENTER);
        title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(8, 0, 12, 0));
        add(title, BorderLayout.NORTH);

        RoundedPanel card = new RoundedPanel(12, CARD);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        // Instruction label
        JLabel lblInstr = new JLabel("Sélectionnez vos abonnements", SwingConstants.CENTER);
        lblInstr.setFont(LABEL_FONT.deriveFont(Font.PLAIN, 16f));
        lblInstr.setForeground(MUTED);
        lblInstr.setBorder(new EmptyBorder(6, 0, 12, 0));
        card.add(lblInstr, BorderLayout.NORTH);

        // Center content: checkboxes laid out with MigLayout
        JPanel center = new JPanel(new MigLayout("", "[grow,center]", "[][][]"));
        center.setOpaque(false);

        JCheckBox checkTisseo = styledCheckbox("Tisseo");
        JCheckBox checkSNCF = styledCheckbox("SNCF");
        JCheckBox checkLio = styledCheckbox("LIO ARC");

        center.add(checkTisseo, "wrap");
        center.add(checkSNCF, "wrap");
        center.add(checkLio, "wrap");

        card.add(center, BorderLayout.CENTER);

        // Save button bottom-right
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        JButton btnEnregistrer = styledButton("Enregistrer", true);
        btnEnregistrer.addActionListener(controleur);
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.add(btnEnregistrer);
        bottom.add(right, BorderLayout.EAST);
        card.add(bottom, BorderLayout.SOUTH);

        // Populate combo model as before (kept for logic parity)
        DefaultComboBoxModel<Abonnement> abonnementModel = new DefaultComboBoxModel<>();
        for (Abonnement abonnement : Abonnement.values()) {
            abonnementModel.addElement(abonnement);
        }

        add(card, BorderLayout.CENTER);
    }

    private JCheckBox styledCheckbox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(FIELD_FONT.deriveFont(Font.PLAIN, 18f));
        cb.setForeground(Color.WHITE);
        cb.setOpaque(false);
        cb.setFocusPainted(false);
        return cb;
    }

    private JButton styledButton(String text, boolean primary) {
        JButton b = new JButton(text);
        b.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primary) {
            b.setBackground(ACCENT);
            b.setForeground(Color.WHITE);
            b.setBorder(new RoundedBorder(new Color(0, 0, 0, 0), 10));
        } else {
            b.setBackground(new Color(28, 36, 52));
            b.setForeground(MUTED);
            b.setBorder(new RoundedBorder(new Color(34, 44, 66), 10, 1));
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
        b.setBorder(BorderFactory.createCompoundBorder(b.getBorder(), BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        return b;
    }
}