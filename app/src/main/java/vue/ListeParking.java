package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controleur.ControleurListeParking;
import modele.Parking;
import modele.dao.DaoParking;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ListeParking extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	// Palette and fonts aligned with GestionVehicule
	private static final Color BG = new Color(22, 28, 38);
	private static final Color PANEL = new Color(28, 36, 52);
	private static final Color CARD = new Color(30, 38, 56);
	private static final Color ACCENT = new Color(100, 170, 255);
	private static final Color ACCENT_HOVER = new Color(85, 150, 235);
	private static final Color MUTED = new Color(170, 185, 205);
	private static final Color FIELD_BG = new Color(26, 34, 46);
	private static final Color FIELD_BORDER = new Color(56, 72, 104);

	private static final Font TITLE_FONT;
	private static final Font FIELD_FONT;

	static {
		String[] prefs = { "San Francisco", "Inter", "Roboto", "Segoe UI", "Noto Sans", "Dialog" };
		String chosen = java.util.Arrays.stream(prefs)
				.filter(n -> java.util.Arrays
						.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
						.contains(n))
				.findFirst().orElse("Dialog");
		TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
		FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
	}

	public ListeParking() {
		super(12, PANEL);
		setPreferredSize(new Dimension(700, 520));
		initialize();
	}

	private void initialize() {
		ControleurListeParking controleur = new ControleurListeParking(this);
		setLayout(new BorderLayout(0, 0));
		setBackground(BG);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel labelTitre = new JLabel("Parking disponibles", SwingConstants.CENTER);
		labelTitre.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
		labelTitre.setForeground(Color.WHITE);
		labelTitre.setBorder(new EmptyBorder(8, 0, 12, 0));
		add(labelTitre, BorderLayout.NORTH);

		RoundedPanel card = new RoundedPanel(12, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(new EmptyBorder(14, 14, 14, 14));

		// Top search bar
		JPanel top = new JPanel(new GridBagLayout());
		top.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.gridx = 0;

		textField = new JTextField();
		textField.setFont(FIELD_FONT);
		textField.setBackground(FIELD_BG);
		textField.setForeground(Color.WHITE);
		textField.setBorder(new RoundedBorder(FIELD_BORDER, 8, 1));
		top.add(textField, gbc);

		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.insets = new Insets(0, 12, 0, 0);
		JButton btnSearch = styledButton("Rechercher", false);
		btnSearch.addActionListener(controleur);
		top.add(btnSearch, gbc);

		card.add(top, BorderLayout.NORTH);

		// Table model
		DefaultTableModel modele = new DefaultTableModel();
		modele.addColumn("Nom");
		modele.addColumn("Adresse");
		modele.addColumn("Hauteur");
		modele.addColumn("Nombre de place");
		modele.addColumn("Places moto");
		modele.addColumn("Places compatibles électrique");

		try {
			List<Parking> res = new DaoParking().findAll();
			for (Parking i : res) {
				Vector<String> newRow = new Vector<>();
				newRow.add(i.getNom());
				newRow.add(i.getAdresse());
				newRow.add(Float.toString(i.getHauteur()));
				newRow.add(Integer.toString(i.getplacesMax()));
				newRow.add(Integer.toString(i.getplacesMaxMoto()));
				newRow.add(Integer.toString(i.getplacesMaxElectrique()));
				modele.addRow(newRow);
			}
		} catch (SQLException e) {
			// ignore as in original
		}

		table = new JTable(modele);
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(40);
		table.setFont(FIELD_FONT);
		table.setForeground(Color.WHITE);
		table.setBackground(CARD);
		table.setSelectionBackground(new Color(46, 58, 82));
		table.setSelectionForeground(Color.WHITE);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFocusable(false);

		// center numeric columns (Hauteur and counts)
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setForeground(MUTED);
		centerRenderer.setBackground(CARD);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(CARD);
		card.add(scroll, BorderLayout.CENTER);

		JPanel sizeLimiter = new JPanel(new BorderLayout());
		sizeLimiter.setOpaque(false);
		sizeLimiter.setPreferredSize(new Dimension(840, 520));
		sizeLimiter.add(card, BorderLayout.CENTER);

		add(sizeLimiter, BorderLayout.CENTER);

		// Optional: allow simple client-side filtering similar to GestionVehicule
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modele);
		table.setRowSorter(sorter);
		btnSearch.addActionListener(e -> {
			String text = textField.getText();
			if (text.trim().length() == 0) {
				sorter.setRowFilter(null);
			} else {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
			}
		});
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