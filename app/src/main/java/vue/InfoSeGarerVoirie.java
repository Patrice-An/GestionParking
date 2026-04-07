package vue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.ControleurInfoSeGarerVoirie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import modele.Tarif;
import modele.Vehicule;
import modele.Zone;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

public class InfoSeGarerVoirie extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Vehicule> choixVehicule;
	private JComboBox<Zone> comboZone;
	private JComboBox<Tarif> comboTarif;
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

	public InfoSeGarerVoirie(App app) {
		super(12, PANEL);
		this.app = app;
		setPreferredSize(new Dimension(1080, 720));
		initialize();
	}

	private void initialize() {
		if (app.getUtilisateur() == null) {
			return;
		}
		ControleurInfoSeGarerVoirie controleur = new ControleurInfoSeGarerVoirie(this, app);

		setLayout(new BorderLayout(0, 0));
		setBackground(BG);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Title
		JLabel titre = new JLabel("Garer en voirie", SwingConstants.CENTER);
		titre.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
		titre.setForeground(Color.WHITE);
		titre.setBorder(BorderFactory.createEmptyBorder(8, 0, 12, 0));
		add(titre, BorderLayout.NORTH);

		// Card center
		RoundedPanel card = new RoundedPanel(12, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
		card.setOpaque(false);

		JPanel center = new JPanel(new BorderLayout(0, 0));
		center.setOpaque(false);

		JLabel titreDonnees = new JLabel("Sélectionner un véhicule", SwingConstants.LEFT);
		titreDonnees.setFont(FIELD_FONT.deriveFont(Font.BOLD, 16f));
		titreDonnees.setForeground(Color.WHITE);
		center.add(titreDonnees, BorderLayout.NORTH);

		JPanel listeInfos = new JPanel();
		listeInfos.setOpaque(false);
		listeInfos.setLayout(new GridLayout(6, 2, 0, 0));

		this.choixVehicule = styledComboBox();
		build();
		listeInfos.add(choixVehicule);

		JLabel labelSelectionVoirie = new JLabel("Sélectionnez la zone utilisé :");
		labelSelectionVoirie.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
		labelSelectionVoirie.setForeground(Color.WHITE);
		listeInfos.add(labelSelectionVoirie);

		this.comboZone = styledComboBox();
		this.comboZone.addItemListener(controleur.controleurVoirieTarif);
		DefaultComboBoxModel<Zone> voirieModel = new DefaultComboBoxModel<>();
		for (Zone z : controleur.getZones()) {
			voirieModel.addElement(z);
		}
		this.comboZone.setModel(voirieModel);
		listeInfos.add(this.comboZone);

		this.comboTarif = styledComboBox();
		controleur.updateComboTarif();
		listeInfos.add(this.comboTarif);

		center.add(listeInfos, BorderLayout.CENTER);
		card.add(center, BorderLayout.CENTER);
		add(card, BorderLayout.CENTER);

		// South panel with buttons
		JPanel panneauSud = new JPanel(new BorderLayout(0, 0));
		panneauSud.setOpaque(false);
		panneauSud.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

		JButton btnValider = styledButton("Valider", true);
		btnValider.addActionListener(controleur);
		panneauSud.add(btnValider, BorderLayout.EAST);

		JButton btnAnnuler = styledButton("Annuler", false);
		btnAnnuler.addActionListener(controleur);
		panneauSud.add(btnAnnuler, BorderLayout.WEST);

		add(panneauSud, BorderLayout.SOUTH);
	}

	private JComboBox styledComboBox() {
		JComboBox cb = new JComboBox<>();
		cb.setFont(FIELD_FONT);
		cb.setBackground(new Color(36, 46, 64));
		cb.setForeground(MUTED);
		cb.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(new Color(34, 44, 66), 12, 1),
				BorderFactory.createEmptyBorder(6, 10, 6, 10)));
		return cb;
	}

	private JButton styledButton(String text, boolean primary) {
		JButton b = new JButton(text);
		b.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
		b.setPreferredSize(new Dimension(140, 40));
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
		b.setBorder(BorderFactory.createCompoundBorder(b.getBorder(), BorderFactory.createEmptyBorder(6, 12, 6, 12)));
		return b;
	}

	public JComboBox<Vehicule> getComboVehicule() {
		return choixVehicule;
	}

	public JComboBox<Zone> getComboZone() {
		return comboZone;
	}

	public JComboBox<Tarif> getComboTarif() {
		return comboTarif;
	}

	private void build() {
		DefaultComboBoxModel<Vehicule> vehiculeModel = new DefaultComboBoxModel<>();
		for (Vehicule v : app.getUtilisateur().getVehicules()) {
			vehiculeModel.addElement(v);
		}
		this.choixVehicule.setModel(vehiculeModel);
	}

	public void refresh() {
		choixVehicule.removeAllItems();
		build();
	}
}