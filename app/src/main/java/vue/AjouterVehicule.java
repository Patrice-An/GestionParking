package vue;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import controleur.ControleurAjouterVehicule;
import modele.Type_vehicule;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

/*
  Amélioration :
  - Utilise la palette et polices cohérentes avec Accueil/GestionVehicule.
  - Disposition plus simple et responsive (BoxLayout + GridBag-like spacing).
  - Champs stylés (background, bord arrondi) et placeholders visuels.
  - Boutons stylés (primaire / secondaire) et spacing amélioré.
  - Aucune logique métier modifiée ; le contrôleur existant est réutilisé.
*/

public class AjouterVehicule extends RoundedPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDesignation;
	private JTextField immatriculationField;
	private JSpinner hauteurField;
	private JComboBox<Type_vehicule> typeVehiculeComboBox;
	private final App app;

	// Palette & fonts (copiées depuis Accueil)
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

	public AjouterVehicule(App app) {
		super(12, PANEL);
		this.app = app;
		initialize();
	}

	private void initialize() {
		ControleurAjouterVehicule controleur = new ControleurAjouterVehicule(this, app);

		setLayout(new BorderLayout());
		setBackground(BG);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		// Title
		JLabel title = new JLabel("Enregistrer un nouveau véhicule", SwingConstants.CENTER);
		title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 18f));
		title.setForeground(Color.WHITE);
		title.setBorder(new EmptyBorder(8, 0, 12, 0));
		add(title, BorderLayout.NORTH);

		// Card container
		RoundedPanel card = new RoundedPanel(10, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(new EmptyBorder(16, 16, 16, 16));

		// Form vertical layout
		JPanel form = new JPanel();
		form.setOpaque(false);
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

		// Désignation
		form.add(fieldLabel("Désignation"));
		txtDesignation = styledTextField("Désignation");
		form.add(txtDesignation);
		form.add(Box.createVerticalStrut(10));

		// Immatriculation
		form.add(fieldLabel("Immatriculation"));
		immatriculationField = styledTextField("AB-123-CD");
		immatriculationField.addActionListener(controleur);
		form.add(immatriculationField);
		form.add(Box.createVerticalStrut(10));

		// Type & Hauteur row
		JPanel row = new JPanel();
		row.setOpaque(false);
		row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

		// Type
		JPanel colType = new JPanel();
		colType.setOpaque(false);
		colType.setLayout(new BoxLayout(colType, BoxLayout.Y_AXIS));
		colType.add(fieldLabel("Type de véhicule"));
		typeVehiculeComboBox = new JComboBox<>(new DefaultComboBoxModel<>(Type_vehicule.values()));
		typeVehiculeComboBox.setFont(FIELD_FONT);
		typeVehiculeComboBox.setBackground(FIELD_BG);
		typeVehiculeComboBox.setForeground(Color.WHITE);
		typeVehiculeComboBox.setBorder(new RoundedBorder(FIELD_BORDER, 6, 1));
		typeVehiculeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		colType.add(typeVehiculeComboBox);
		row.add(colType);
		row.add(Box.createHorizontalStrut(12));

		// Hauteur
		JPanel colHauteur = new JPanel();
		colHauteur.setOpaque(false);
		colHauteur.setLayout(new BoxLayout(colHauteur, BoxLayout.Y_AXIS));
		colHauteur.add(fieldLabel("Hauteur (m)"));
		hauteurField = new JSpinner(
				new SpinnerNumberModel(Float.valueOf(0f), Float.valueOf(0f), Float.valueOf(10f), Float.valueOf(0.1f)));
		hauteurField.setFont(FIELD_FONT);
		hauteurField.setBorder(new RoundedBorder(FIELD_BORDER, 6, 1));
		hauteurField.setBackground(FIELD_BG);
		hauteurField.setForeground(Color.WHITE);
		hauteurField.setPreferredSize(new Dimension(120, 32));
		hauteurField.setMaximumSize(new Dimension(160, 32));
		colHauteur.add(hauteurField);
		row.add(colHauteur);

		form.add(row);
		form.add(Box.createVerticalStrut(18));

		// Buttons row
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		JButton btnAnnuler = styledButton("Annuler", false);
		btnAnnuler.addActionListener(controleur);
		JButton btnEnregistrer = styledButton("Enregistrer", true);
		btnEnregistrer.addActionListener(controleur);

		buttons.add(Box.createHorizontalGlue());
		buttons.add(btnAnnuler);
		buttons.add(Box.createHorizontalStrut(12));
		buttons.add(btnEnregistrer);

		form.add(buttons);

		card.add(form, BorderLayout.CENTER);

		// size limiter
		JPanel centerWrapper = new JPanel(new BorderLayout());
		centerWrapper.setOpaque(false);
		centerWrapper.add(card, BorderLayout.CENTER);

		JPanel sizeLimiter = new JPanel(new BorderLayout());
		sizeLimiter.setOpaque(false);
		sizeLimiter.setPreferredSize(new Dimension(640, 360));
		sizeLimiter.add(centerWrapper, BorderLayout.CENTER);

		add(sizeLimiter, BorderLayout.CENTER);
	}

	private JLabel fieldLabel(String text) {
		JLabel l = new JLabel(text);
		l.setFont(LABEL_FONT.deriveFont(Font.BOLD, 13f));
		l.setForeground(Color.WHITE);
		l.setBorder(new EmptyBorder(4, 0, 6, 0));
		return l;
	}

	private JTextField styledTextField(String placeholder) {
		JTextField t = new JTextField(placeholder);
		t.setFont(FIELD_FONT);
		t.setForeground(new Color(200, 200, 200));
		t.setBackground(FIELD_BG);
		t.setBorder(new RoundedBorder(FIELD_BORDER, 6, 1));
		t.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
		return t;
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
		b.setBorder(BorderFactory.createCompoundBorder(b.getBorder(),
				BorderFactory.createEmptyBorder(8, 14, 8, 14)));
		return b;
	}

	public String getDesignation() {
		return txtDesignation.getText();
	}

	public String getImmatriculation() {
		return immatriculationField.getText();
	}

	public float getHauteur() {
		Object v = hauteurField.getValue();
		if (v instanceof Number)
			return ((Number) v).floatValue();
		return 0f;
	}

	public Type_vehicule getTypeVehicule() {
		return (Type_vehicule) typeVehiculeComboBox.getSelectedItem();
	}
}
