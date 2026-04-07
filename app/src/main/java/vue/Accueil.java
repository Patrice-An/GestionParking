package vue;

import controleur.ControleurAccueil;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Accueil extends RoundedPanel {
    private static final long serialVersionUID = 2L;

    private final App app;

    // Palette: matched to Inscription & SeConnecter — still dark overall, with a
    // softer, lighter blue accent
    private static final Color BG = new Color(22, 28, 38); // slightly lighter main background
    private static final Color PANEL = new Color(28, 36, 52); // card container darker but warmer
    private static final Color CARD = new Color(30, 38, 56); // card surface close to panel
    private static final Color ACCENT = new Color(100, 170, 255); // lighter, friendly blue accent
    private static final Color ACCENT_HOVER = new Color(85, 150, 235); // hover shade for accent buttons
    private static final Color MUTED = new Color(170, 185, 205); // slight cool tint for secondary text
    private static final Color FIELD_BG = new Color(26, 34, 46); // form field background a bit lighter
    private static final Color FIELD_BORDER = new Color(56, 72, 104); // softer border color

    private static final Font TITLE_FONT;
    private static final Font LABEL_FONT;
    private static final Font FIELD_FONT;

    static {
        String[] prefs = { "San Francisco", "Inter", "Roboto", "Segoe UI", "Noto Sans", "Dialog" };
        String chosen = Arrays.stream(prefs)
                .filter(n -> Arrays
                        .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
                        .contains(n))
                .findFirst().orElse("Dialog");
        TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
        LABEL_FONT = new Font(chosen, Font.PLAIN, 13);
        FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
    }

    public Accueil(App app) {
        super(16, PANEL);
        this.app = app;
        initialize();
    }

    private void initialize() {
        ControleurAccueil controleur = new ControleurAccueil(this.app);

        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Top title
        JLabel title = new JLabel("Bonjour " + app.getUtilisateur().toString(), SwingConstants.CENTER);
        title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(8, 0, 12, 0));
        add(title, BorderLayout.NORTH);

        // Card container
        RoundedPanel card = new RoundedPanel(12, CARD);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        // Optional subtle separator (like Inscription)
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(46, 58, 82)); // subtle, slightly bluish divider to match other pages
        sep.setPreferredSize(new Dimension(1, 1));
        card.add(sep, BorderLayout.NORTH);

        // Center content with constrained width
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(14, 14, 14, 14);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Buttons column (use MigLayout-like spacing using insets)
        JPanel btnColumn = new JPanel();
        btnColumn.setOpaque(false);
        btnColumn.setLayout(new BoxLayout(btnColumn, BoxLayout.Y_AXIS));
        int gap = 12;

        JButton btnSeGarer = styledButton("Se Garer", false);
        btnSeGarer.setPreferredSize(new Dimension(420, 60));
        btnSeGarer.addActionListener(controleur);
        btnColumn.add(wrapButton(btnSeGarer));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnHistorique = styledButton("Historique des Stationnements", false);
        btnHistorique.setPreferredSize(new Dimension(420, 60));
        btnHistorique.addActionListener(controleur);
        btnColumn.add(wrapButton(btnHistorique));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnGererVehicule = styledButton("Gérer mes Véhicules", false);
        btnGererVehicule.setPreferredSize(new Dimension(420, 60));
        btnGererVehicule.addActionListener(controleur);
        btnColumn.add(wrapButton(btnGererVehicule));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnListeParking = styledButton("Parkings disponibles", false);
        btnListeParking.setPreferredSize(new Dimension(420, 60));
        btnListeParking.addActionListener(controleur);
        btnColumn.add(wrapButton(btnListeParking));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnGestionAbonnement = styledButton("Gestion abonnement", false);
        btnGestionAbonnement.setPreferredSize(new Dimension(420, 60));
        btnGestionAbonnement.addActionListener(controleur);
        btnColumn.add(wrapButton(btnGestionAbonnement));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnDeconnexion = styledButton("Se déconnecter", false);
        btnDeconnexion.setPreferredSize(new Dimension(420, 60));
        btnDeconnexion.addActionListener(controleur);
        btnColumn.add(wrapButton(btnDeconnexion));
        btnColumn.add(Box.createRigidArea(new Dimension(0, gap)));

        JButton btnPartir = styledButton("Quitter l'application", false);
        btnPartir.setPreferredSize(new Dimension(420, 60));
        btnPartir.addActionListener(controleur);
        btnColumn.add(wrapButton(btnPartir));

        content.add(btnColumn, gbc);
        card.add(content, BorderLayout.CENTER);

        // Center wrapper to limit width (like Inscription)
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(card, gbc2);

        int maxWidth = 640;
        int prefHeight = 520;
        JPanel sizeLimiter = new JPanel(new BorderLayout());
        sizeLimiter.setOpaque(false);
        sizeLimiter.setPreferredSize(new Dimension(maxWidth, prefHeight));
        sizeLimiter.add(centerWrapper, BorderLayout.CENTER);

        add(sizeLimiter, BorderLayout.CENTER);
    }

    private JPanel wrapButton(JButton b) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        p.add(b);
        return p;
    }

    private JButton styledButton(String text, boolean primary) {
        JButton b = new JButton(text);
        b.setFont(FIELD_FONT.deriveFont(Font.BOLD, 16f));
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
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (primary)
                    b.setBackground(ACCENT_HOVER);
                else
                    b.setBackground(new Color(36, 46, 64));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (primary)
                    b.setBackground(ACCENT);
                else
                    b.setBackground(new Color(28, 36, 52));
            }
        });
        // apply padding similar to fields
        b.setBorder(BorderFactory.createCompoundBorder(b.getBorder(),
                BorderFactory.createEmptyBorder(12, 18, 12, 18)));
        return b;
    }
}