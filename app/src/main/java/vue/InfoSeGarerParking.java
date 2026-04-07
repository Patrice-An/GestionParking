package vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import controleur.ControleurInfoSeGarer;
import modele.Parking;
import modele.Vehicule;
import modele.dao.DaoParking;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.util.Arrays;

public class InfoSeGarerParking extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private LocalDateTime heureArrive;
	private JComboBox<Vehicule> choixVehicule;
	private JComboBox<Parking> deroulantSelectionParking;
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

	public InfoSeGarerParking(App app) {
		super(12, PANEL);
		this.app = app;
		FlatDarculaLaf.setup();
		setPreferredSize(new Dimension(1080, 720));
		initialize();
	}

	private void initialize() {
		ControleurInfoSeGarer controleur = new ControleurInfoSeGarer(this, app);

		setLayout(new BorderLayout(0, 0));
		setBackground(BG);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Title (north)
		JLabel titre = new JLabel("Sélectionner un véhicule", SwingConstants.CENTER);
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

		JPanel listeInfos = new JPanel();
		listeInfos.setOpaque(false);

		final String rowSpecSize = "125px";

		listeInfos.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("220px"),
				ColumnSpec.decode("220px:grow"), },
				new RowSpec[] {
						FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
						RowSpec.decode(rowSpecSize),
						RowSpec.decode(rowSpecSize),
						RowSpec.decode(rowSpecSize),
						RowSpec.decode(rowSpecSize), }));

		JLabel labelHeureArrive = new JLabel("Heure d'arrivée :");
		labelHeureArrive.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
		labelHeureArrive.setForeground(Color.WHITE);
		listeInfos.add(labelHeureArrive, "1, 2, fill, fill");

		heureArrive = LocalDateTime.now();
		JLabel heureLabel = new JLabel(String.format("%02d:%02d", heureArrive.getHour(), heureArrive.getMinute()));
		heureLabel.setFont(FIELD_FONT);
		heureLabel.setForeground(MUTED);
		listeInfos.add(heureLabel, "2, 2, fill, fill");

		JLabel labelChoixVehicule = new JLabel("Choix véhicule :");
		labelChoixVehicule.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
		labelChoixVehicule.setForeground(Color.WHITE);
		listeInfos.add(labelChoixVehicule, "1, 3, fill, fill");

		this.choixVehicule = styledComboBox();
		build();
		listeInfos.add(choixVehicule, "2, 3, fill, fill");

		JLabel labelSelectionParking = new JLabel("Sélectionnez le Parking utilisé :");
		labelSelectionParking.setFont(FIELD_FONT.deriveFont(Font.BOLD, 14f));
		labelSelectionParking.setForeground(Color.WHITE);
		listeInfos.add(labelSelectionParking, "1, 4, fill, fill");

		JLabel spacer = new JLabel(" ");
		spacer.setForeground(MUTED);
		listeInfos.add(spacer, "2, 5, fill, fill");

		deroulantSelectionParking = styledComboBox();
		DefaultComboBoxModel<Parking> parkingModel = new DefaultComboBoxModel<>();
		try {
			for (Parking p : new DaoParking().findAll()) {
				parkingModel.addElement(p);
			}
		} catch (SQLException eee) {
			// empty
		}
		deroulantSelectionParking.setModel(parkingModel);
		listeInfos.add(deroulantSelectionParking, "1, 5, 2, 1, fill, fill");

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

	public LocalDateTime getHeureArrive() {
		return heureArrive;
	}

	public JComboBox<Vehicule> getChoixVehicule() {
		return choixVehicule;
	}

	public JComboBox<Parking> getDeroulantSelectionParking() {
		return deroulantSelectionParking;
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