package vue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import controleur.ControleurHistorique;

import modele.Parking;
import modele.Zone;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.BoxLayout;

/*
  Styled to match Accueil page:
  - Uses same palette, fonts, rounded panels and button styling.
  - Keeps original control flow and action listeners.
  - Converts the vertical Box + LineBorder items into rounded "cards" (table-like rows)
    while preserving properties and listeners.
*/

public class HistoriqueStationnement extends RoundedPanel {

	private static final long serialVersionUID = 1L;
	private App fenetreprincipal;
	private ControleurHistorique controleur;
	private JPanel paneParking;
	private JPanel paneZone;
	private JButton btnPartir;

	// Palette and fonts copied from Accueil
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
		String chosen = Arrays.stream(prefs)
				.filter(n -> Arrays
						.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
						.contains(n))
				.findFirst().orElse("Dialog");
		TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
		LABEL_FONT = new Font(chosen, Font.PLAIN, 13);
		FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
	}

	public HistoriqueStationnement(App fenetreprincipal) {
		super(12, PANEL);
		this.fenetreprincipal = fenetreprincipal;
		controleur = new ControleurHistorique(this, this.fenetreprincipal);
		initialize();
		this.build(); // keep original build invocation (will reuse methods below)
	}

	private void initialize() {
		setLayout(new BorderLayout());
		setBackground(BG);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel title = new JLabel("Historique de stationnement", SwingConstants.CENTER);
		title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
		title.setForeground(Color.WHITE);
		title.setBorder(new EmptyBorder(8, 0, 12, 0));
		add(title, BorderLayout.NORTH);

		// Center card container to match Accueil style
		RoundedPanel card = new RoundedPanel(12, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(new EmptyBorder(14, 14, 14, 14));

		JSeparator sep = new JSeparator();
		sep.setForeground(new Color(46, 58, 82));
		sep.setPreferredSize(new Dimension(1, 1));
		card.add(sep, BorderLayout.NORTH);

		// content area
		JPanel content = new JPanel(new GridBagLayout());
		content.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;

		// Vertical container for parking and zone sections
		JPanel centerColumn = new JPanel();
		// put centerColumn inside a scroll pane so many items become scrollable
		JScrollPane centerScroll = new JScrollPane(centerColumn);
		centerScroll.setBorder(null);
		centerScroll.getViewport().setOpaque(false);
		centerScroll.getViewport().setBackground(CARD);
		centerScroll.setOpaque(false);
		centerScroll.getVerticalScrollBar().setUnitIncrement(12);
		content.add(centerScroll, gbc);
		card.add(content, BorderLayout.CENTER);

		centerColumn.setOpaque(false);
		centerColumn.setLayout(new BoxLayout(centerColumn, BoxLayout.Y_AXIS));

		// Parking section wrapper (rounded)
		RoundedPanel paneParentParking = new RoundedPanel(10, FIELD_BG);
		paneParentParking.setLayout(new BorderLayout(8, 8));
		paneParentParking.setBorder(new EmptyBorder(12, 12, 12, 12));

		JLabel lblParking = new JLabel("Dans un parking :", SwingConstants.CENTER);
		lblParking.setFont(LABEL_FONT.deriveFont(Font.BOLD, 14f));
		lblParking.setForeground(Color.WHITE);
		paneParentParking.add(lblParking, BorderLayout.NORTH);

		paneParking = new JPanel();
		paneParking.setOpaque(false);
		paneParking.setLayout(new BoxLayout(paneParking, BoxLayout.Y_AXIS));
		paneParentParking.add(paneParking, BorderLayout.CENTER);

		centerColumn.add(paneParentParking);
		centerColumn.add(Box.createVerticalStrut(12));

		// Separator
		JSeparator centerSep = new JSeparator();
		centerSep.setForeground(new Color(46, 58, 82));
		centerSep.setPreferredSize(new Dimension(1, 1));
		centerColumn.add(centerSep);
		centerColumn.add(Box.createVerticalStrut(12));

		// Zone section wrapper
		RoundedPanel paneParentZone = new RoundedPanel(10, FIELD_BG);
		paneParentZone.setLayout(new BorderLayout(8, 8));
		paneParentZone.setBorder(new EmptyBorder(12, 12, 12, 12));

		JLabel lblZone = new JLabel("En voirie :", SwingConstants.CENTER);
		lblZone.setFont(LABEL_FONT.deriveFont(Font.BOLD, 14f));
		lblZone.setForeground(Color.WHITE);
		paneParentZone.add(lblZone, BorderLayout.NORTH);

		paneZone = new JPanel();
		paneZone.setOpaque(false);
		paneZone.setLayout(new BoxLayout(paneZone, BoxLayout.Y_AXIS));
		paneParentZone.add(paneZone, BorderLayout.CENTER);

		centerColumn.add(paneParentZone);

		content.add(centerColumn, gbc);
		card.add(content, BorderLayout.CENTER);

		JPanel centerWrapper = new JPanel(new GridBagLayout());
		centerWrapper.setOpaque(false);
		centerWrapper.add(card);

		JPanel sizeLimiter = new JPanel(new BorderLayout());
		sizeLimiter.setOpaque(false);
		sizeLimiter.setPreferredSize(new Dimension(640, 520));
		sizeLimiter.add(centerWrapper, BorderLayout.CENTER);

		add(sizeLimiter, BorderLayout.CENTER);

		// South buttons (keeps original layout but styled)
		JPanel paneSouth = new JPanel(new BorderLayout());
		paneSouth.setOpaque(false);
		paneSouth.setBorder(new EmptyBorder(12, 0, 0, 0));

		JButton btnPrec = styledButton("Annuler", false);
		btnPrec.setHorizontalAlignment(SwingConstants.LEFT);
		paneSouth.add(btnPrec, BorderLayout.WEST);

		add(paneSouth, BorderLayout.SOUTH);
	}

	public void build() {
		// Clear existing contents so repeated builds (called from constructor or
		// refresh) won't duplicate
		if (paneParking != null)
			paneParking.removeAll();
		if (paneZone != null)
			paneZone.removeAll();

		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (parking.isPresent()) {
					// create a card-like row for each parking record
					RoundedPanel row = new RoundedPanel(8, CARD);
					row.setLayout(new BorderLayout(8, 8));
					row.setBorder(new EmptyBorder(10, 10, 10, 10));
					row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

					JPanel labels = new JPanel(new GridBagLayout());
					labels.setOpaque(false);
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.anchor = GridBagConstraints.WEST;
					gbc.fill = GridBagConstraints.HORIZONTAL;

					JLabel LabelNom = new JLabel("Parking: " + parking.get().getNom());
					LabelNom.setFont(LABEL_FONT.deriveFont(Font.BOLD, 13f));
					LabelNom.setForeground(Color.WHITE);
					labels.add(LabelNom, gbc);

					JLabel LabelVehicule = new JLabel("Véhicule: " + res.getString(3));
					LabelVehicule.setFont(FIELD_FONT);
					LabelVehicule.setForeground(MUTED);
					gbc.gridy = 1;
					labels.add(LabelVehicule, gbc);

					JLabel LabelArrivee = new JLabel("De: " + res.getString(5));
					LabelArrivee.setFont(FIELD_FONT);
					LabelArrivee.setForeground(MUTED);
					gbc.gridy = 2;
					labels.add(LabelArrivee, gbc);

					gbc.gridy = 3;
					if (res.getString(6) != null) {
						JLabel LabelSortie = new JLabel("A: " + res.getString(6));
						LabelSortie.setFont(FIELD_FONT);
						LabelSortie.setForeground(MUTED);
						labels.add(LabelSortie, gbc);

						JButton btnFacture = styledButton("Facture", true);
						btnFacture.putClientProperty("vehiculeImmat", res.getString(3));
						btnFacture.putClientProperty("utilisateur", null);
						btnFacture.putClientProperty("isParking", true);
						btnFacture.putClientProperty("parkingID", parking.get().getidParking());
						btnFacture.putClientProperty("arrivee", res.getString(5));
						btnFacture.putClientProperty("depart", res.getString(6));
						btnFacture.addActionListener(controleur);

						row.add(labels, BorderLayout.CENTER);
						row.add(wrapButtonPanel(btnFacture), BorderLayout.EAST);
					} else {
						JLabel LabelSortie = new JLabel("A: Maintenant");
						LabelSortie.setFont(FIELD_FONT);
						LabelSortie.setForeground(MUTED);
						labels.add(LabelSortie, gbc);

						JButton btnPartirLocal = styledButton("Partir", false);
						btnPartirLocal.putClientProperty("isParking", true);
						btnPartirLocal.putClientProperty("vehiculeImmat", res.getString(3));
						btnPartirLocal.putClientProperty("arrivee", res.getString(5));
						btnPartirLocal.putClientProperty("parkingID", parking.get().getidParking());
						btnPartirLocal.addActionListener(controleur);

						row.add(labels, BorderLayout.CENTER);
						row.add(wrapButtonPanel(btnPartirLocal), BorderLayout.EAST);
					}

					paneParking.add(row);
					paneParking.add(Box.createVerticalStrut(8));
				}
			}
		} catch (SQLException ee) {
			// original code swallowed exceptions; keep same behavior
		}

		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Zone> zone = new DaoZone().findById(res.getInt(1));
				if (zone.isPresent()) {
					RoundedPanel row = new RoundedPanel(8, CARD);
					row.setLayout(new BorderLayout(8, 8));
					row.setBorder(new EmptyBorder(10, 10, 10, 10));
					row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

					JPanel labels = new JPanel(new GridBagLayout());
					labels.setOpaque(false);
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.anchor = GridBagConstraints.WEST;
					gbc.fill = GridBagConstraints.HORIZONTAL;

					JLabel labelNom = new JLabel("Zone: " + zone.get().getCouleur());
					labelNom.setFont(LABEL_FONT.deriveFont(Font.BOLD, 13f));
					labelNom.setForeground(Color.WHITE);
					labels.add(labelNom, gbc);

					JLabel labelVehicule = new JLabel("Véhicule: " + res.getString(3));
					labelVehicule.setFont(FIELD_FONT);
					labelVehicule.setForeground(MUTED);
					gbc.gridy = 1;
					labels.add(labelVehicule, gbc);

					JLabel labelArrivee = new JLabel("De: " + res.getString(5));
					labelArrivee.setFont(FIELD_FONT);
					labelArrivee.setForeground(MUTED);
					gbc.gridy = 2;
					labels.add(labelArrivee, gbc);

					gbc.gridy = 3;
					if (res.getString(6) != null) {
						JLabel labelSortie = new JLabel("A: " + res.getString(6));
						labelSortie.setFont(FIELD_FONT);
						labelSortie.setForeground(MUTED);
						labels.add(labelSortie, gbc);

						JButton btnFacture = styledButton("Facture", true);
						btnFacture.putClientProperty("vehiculeImmat", res.getString(3));
						btnFacture.putClientProperty("utilisateur", null);
						btnFacture.putClientProperty("isParking", false);
						btnFacture.putClientProperty("zoneCouleur", zone.get().getId());
						btnFacture.putClientProperty("arrivee", res.getString(5));
						btnFacture.putClientProperty("depart", res.getString(6));
						btnFacture.addActionListener(controleur);

						row.add(labels, BorderLayout.CENTER);
						row.add(wrapButtonPanel(btnFacture), BorderLayout.EAST);
					} else {
						JLabel labelSortie = new JLabel("A: Maintenant");
						labelSortie.setFont(FIELD_FONT);
						labelSortie.setForeground(MUTED);
						labels.add(labelSortie, gbc);

						btnPartir = styledButton("Partir", false);
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.addActionListener(controleur);

						row.add(labels, BorderLayout.CENTER);
						row.add(wrapButtonPanel(btnPartir), BorderLayout.EAST);
					}

					paneZone.add(row);
					paneZone.add(Box.createVerticalStrut(8));
				}
			}
		} catch (SQLException ee) {
			// keep original behavior
		}

		// revalidate/repaint to reflect UI changes
		if (paneParking != null) {
			paneParking.revalidate();
			paneParking.repaint();
		}
		if (paneZone != null) {
			paneZone.revalidate();
			paneZone.repaint();
		}
	}

	public void refresh() {
		if (paneZone != null)
			paneZone.removeAll();
		if (paneParking != null)
			paneParking.removeAll();
		build();
	}

	public JButton getbtnPartir() {
		return btnPartir;
	}

	/* Helper methods mirroring styles from Accueil */

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
		b.setBorder(javax.swing.BorderFactory.createCompoundBorder(b.getBorder(),
				javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
		return b;
	}

	private JPanel wrapButtonPanel(JButton b) {
		JPanel p = new JPanel(new GridBagLayout());
		p.setOpaque(false);
		p.add(b);
		return p;
	}
}
