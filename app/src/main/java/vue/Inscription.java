package vue;

import controleur.ControleurInscription;
import net.miginfocom.swing.MigLayout;
import vue.components.CompoundRoundedBorder;
import vue.components.DateComponentFormatter;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Inscription extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField mailField;
	private JDatePickerImpl naissancePicker;
	private JTextField telField;
	private JPasswordField passwordField;
	private App app;

	// Palette: still dark overall, with a softer, lighter blue accent and warmer
	// muted tones
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

	public Inscription(App app) {
		super(16, PANEL);
		this.app = app;
		this.initialize();
	}

	private void initialize() {
		ControleurInscription controleur = new ControleurInscription(this, app);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(BG);
		// Top title: horizontally centered at the very top
		JLabel title = new JLabel("Inscription", SwingConstants.CENTER);
		title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
		title.setForeground(Color.WHITE);
		title.setBorder(new EmptyBorder(8, 0, 12, 0));
		title.setOpaque(false);
		add(title, BorderLayout.NORTH);

		// Card (form container)
		RoundedPanel card = new RoundedPanel(12, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(new EmptyBorder(14, 14, 14, 14));

		// Form grid without large gaps between columns — labels close to fields
		JPanel form = new JPanel(new MigLayout("insets 0, gapy 8", "[120px][grow]", "[][][][][][]"));
		form.setOpaque(false);

		form.add(styledLabel("Nom :"), "cell 0 0, aligny center");
		nomField = compactField();
		form.add(nomField, "cell 1 0, growx");

		form.add(styledLabel("Prénom :"), "cell 0 1, aligny center");
		prenomField = compactField();
		form.add(prenomField, "cell 1 1, growx");

		form.add(styledLabel("Mail :"), "cell 0 2, aligny center");
		mailField = compactField();
		form.add(mailField, "cell 1 2, growx");

		UtilDateModel modelDate = new UtilDateModel();
		java.util.Properties p = new java.util.Properties();
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, p);
		naissancePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		modelDate.setSelected(true);
		styleDatePicker(naissancePicker);

		form.add(styledLabel("Date de naissance :"), "cell 0 3, aligny center");
		form.add(naissancePicker, "cell 1 3, growx");

		form.add(styledLabel("Numéro de téléphone :"), "cell 0 4, aligny center");
		telField = compactField();
		form.add(telField, "cell 1 4, growx");

		form.add(styledLabel("Mot de passe :"), "cell 0 5, aligny center");
		passwordField = compactPasswordField();
		form.add(passwordField, "cell 1 5, growx");

		card.add(form, BorderLayout.CENTER);

		// Actions area with subtle divider
		JPanel actions = new JPanel(new BorderLayout());
		actions.setOpaque(false);
		JSeparator sep = new JSeparator();
		sep.setForeground(new Color(46, 58, 82)); // subtle, slightly bluish divider
		sep.setPreferredSize(new Dimension(1, 1));
		actions.add(sep, BorderLayout.NORTH);

		JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
		btns.setOpaque(false);

		JButton btnConnection = styledButton("Connectez-vous", false);
		btnConnection.setPreferredSize(new Dimension(180, 44));
		btnConnection.addActionListener(controleur);
		btns.add(btnConnection);

		JButton btnValider = styledButton("Valider", true);
		btnValider.setPreferredSize(new Dimension(180, 44));
		btnValider.addActionListener(controleur);
		btns.add(btnValider);

		actions.add(btns, BorderLayout.CENTER);
		card.add(actions, BorderLayout.SOUTH);

		// Center container to keep card centered and constrain max width
		JPanel centerWrapper = new JPanel(new GridBagLayout());
		centerWrapper.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		centerWrapper.add(card, gbc);

		// Optional: limit card max width by wrapping in another panel with fixed
		// preferred size
		int maxWidth = 640; // adjust as desired
		int prefHeight = 520;
		JPanel sizeLimiter = new JPanel(new BorderLayout());
		sizeLimiter.setOpaque(false);
		sizeLimiter.setPreferredSize(new Dimension(maxWidth, prefHeight));
		sizeLimiter.add(centerWrapper, BorderLayout.CENTER);

		add(sizeLimiter, BorderLayout.CENTER);
	}

	private JLabel styledLabel(String text) {
		JLabel l = new JLabel(text);
		l.setFont(LABEL_FONT);
		l.setForeground(new Color(200, 210, 225)); // slightly brighter
		return l;
	}

	// compact fields — less contrast, thinner borders
	private JTextField compactField() {
		JTextField f = new JTextField();
		f.setFont(FIELD_FONT);
		f.setForeground(Color.WHITE);
		f.setBackground(FIELD_BG);
		f.setCaretColor(Color.WHITE);
		f.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(FIELD_BORDER, 8, 1),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		addFocusGlow(f);
		return f;
	}

	private JPasswordField compactPasswordField() {
		JPasswordField p = new JPasswordField();
		p.setFont(FIELD_FONT);
		p.setForeground(Color.WHITE);
		p.setBackground(FIELD_BG);
		p.setCaretColor(Color.WHITE);
		p.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(FIELD_BORDER, 8, 1),
				BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		addFocusGlow(p);
		return p;
	}

	private JButton styledButton(String text, boolean primary) {
		JButton b = new JButton(text);
		b.setFont(FIELD_FONT.deriveFont(Font.BOLD));
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setOpaque(true);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (primary) {
			b.setBackground(ACCENT);
			b.setForeground(Color.WHITE);
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
		b.setBorder(new RoundedBorder(new Color(0, 0, 0, 0), 10));
		return b;
	}

	private void styleDatePicker(JDatePickerImpl picker) {
		Component comp = picker.getComponent(0);
		if (comp instanceof JComponent) {
			JComponent jp = (JComponent) comp;
			jp.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(FIELD_BORDER, 8, 1),
					BorderFactory.createEmptyBorder(6, 8, 6, 8)));
			jp.setBackground(FIELD_BG);
			for (Component c : jp.getComponents()) {
				c.setBackground(FIELD_BG);
				c.setForeground(Color.WHITE);
				if (c instanceof JTextField) {
					((JTextField) c).setCaretColor(Color.WHITE);
					((JTextField) c).setBorder(null);
					((JTextField) c).setFont(FIELD_FONT);
				}
			}
		}
	}

	private void addFocusGlow(JComponent comp) {
		comp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comp.setBorder(new CompoundRoundedBorder(new Color(80, 140, 255, 140), 8, 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				comp.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(FIELD_BORDER, 8, 1),
						BorderFactory.createEmptyBorder(8, 10, 8, 10)));
			}
		});
	}

	/* Getters unchanged */
	public JTextField getNomField() {
		return nomField;
	}

	public JTextField getPrenomField() {
		return prenomField;
	}

	public JTextField getMailField() {
		return mailField;
	}

	public JDatePickerImpl getNaissancePicker() {
		return naissancePicker;
	}

	public JTextField getTelField() {
		return telField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}