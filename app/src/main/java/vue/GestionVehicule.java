package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import controleur.ControleurGestionVehicule;
import modele.Vehicule;
import vue.components.RoundedBorder;
import vue.components.RoundedPanel;

/**
 * GestionVehicule — table-based version styled to match Accueil.
 * - Uses JTable with a custom model for vehicles.
 * - Side action panel operates on the selected row (Modify / Delete).
 * - Keeps controller wiring and passes Vehicule objects to controller via
 * client properties.
 */
public class GestionVehicule extends RoundedPanel {

	private static final long serialVersionUID = 1L;
	private final App fenetre;
	private JTable table;
	private VehiculeTableModel model;
	private ControleurGestionVehicule controleur;

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
				.filter(n -> java.util.Arrays
						.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames())
						.contains(n))
				.findFirst().orElse("Dialog");
		TITLE_FONT = new Font(chosen, Font.PLAIN, 20);
		LABEL_FONT = new Font(chosen, Font.PLAIN, 13);
		FIELD_FONT = new Font(chosen, Font.PLAIN, 13);
	}

	public GestionVehicule(App fenetre) {
		super(12, PANEL);
		this.fenetre = fenetre;
		setPreferredSize(new Dimension(700, 520));
		initialize();
	}

	private void initialize() {
		controleur = new ControleurGestionVehicule(fenetre);
		setLayout(new BorderLayout(0, 0));
		setBackground(BG);
		setBorder(new EmptyBorder(20, 20, 20, 20));

		// Title
		JLabel title = new JLabel("Gestion des véhicules", SwingConstants.CENTER);
		title.setFont(TITLE_FONT.deriveFont(Font.BOLD, 20f));
		title.setForeground(Color.WHITE);
		title.setBorder(new EmptyBorder(8, 0, 12, 0));
		add(title, BorderLayout.NORTH);

		// Card container
		RoundedPanel card = new RoundedPanel(12, CARD);
		card.setLayout(new BorderLayout());
		card.setBorder(new EmptyBorder(14, 14, 14, 14));

		// Top controls (search + add)
		JPanel top = new JPanel(new GridBagLayout());
		top.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.gridx = 0;

		JTextField searchField = new JTextField();
		searchField.setFont(FIELD_FONT);
		searchField.setBackground(FIELD_BG);
		searchField.setForeground(Color.WHITE);
		searchField.setBorder(new RoundedBorder(FIELD_BORDER, 8, 1));
		top.add(searchField, gbc);

		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.insets = new java.awt.Insets(0, 12, 0, 0);
		JButton btnSearch = styledButton("Rechercher", false);
		top.add(btnSearch, gbc);

		gbc.gridx = 2;
		gbc.insets = new java.awt.Insets(0, 12, 0, 0);
		JButton btnAjout = styledButton("Ajouter un véhicule", true);
		btnAjout.addActionListener(controleur);
		top.add(btnAjout, gbc);

		card.add(top, BorderLayout.NORTH);

		// Table model and table
		model = new VehiculeTableModel(fenetre.getUtilisateur().getVehicules());
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(40);
		table.setFont(FIELD_FONT);
		table.setForeground(Color.WHITE);
		table.setBackground(CARD);
		table.setSelectionBackground(new Color(46, 58, 82));
		table.setSelectionForeground(Color.WHITE);
		table.setFocusable(false);

		// Center align certain columns
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setForeground(MUTED);
		centerRenderer.setBackground(CARD);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Désignation
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Type
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Immat
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Hauteur

		// Sorter and search/filter
		TableRowSorter<VehiculeTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		btnSearch.addActionListener(e -> {
			String text = searchField.getText();
			if (text.trim().length() == 0) {
				sorter.setRowFilter(null);
			} else {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(text)));
			}
		});

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(CARD);
		card.add(scroll, BorderLayout.CENTER);

		// Right-side action panel (acts on selected row)
		JPanel actions = new JPanel();
		actions.setOpaque(false);
		actions.setLayout(new BoxLayout(actions, BoxLayout.Y_AXIS));
		actions.setBorder(new EmptyBorder(6, 12, 6, 6));

		JButton btnModifier = styledButton("Modifier", false);
		JButton btnSupprimer = styledButton("Supprimer", false);

		btnModifier.addActionListener(e -> {
			int sel = table.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Sélectionnez un véhicule.");
				return;
			}
			int modelIndex = table.convertRowIndexToModel(sel);
			Vehicule v = model.getVehiculeAt(modelIndex);
			btnModifier.putClientProperty("vehicule", v);
			btnModifier.putClientProperty("désignation", null);
			btnModifier.putClientProperty("type", null);
			btnModifier.putClientProperty("hauteur", null);
			btnModifier.doClick(0); // trigger action listener in controller context (controller reads client props)
			controleur.actionPerformed(new java.awt.event.ActionEvent(btnModifier, 0, "Modifier"));
		});

		btnSupprimer.addActionListener(e -> {
			int sel = table.getSelectedRow();
			if (sel == -1) {
				JOptionPane.showMessageDialog(this, "Sélectionnez un véhicule.");
				return;
			}
			int modelIndex = table.convertRowIndexToModel(sel);
			Vehicule v = model.getVehiculeAt(modelIndex);
			btnSupprimer.putClientProperty("vehicule", v);
			controleur.actionPerformed(new java.awt.event.ActionEvent(btnSupprimer, 0, "Supprimer"));
		});

		actions.add(btnModifier);
		actions.add(Box.createVerticalStrut(12));
		actions.add(btnSupprimer);

		// Combine table + actions in a split layout
		JPanel centerWrapper = new JPanel(new BorderLayout());
		centerWrapper.setOpaque(false);
		centerWrapper.add(scroll, BorderLayout.CENTER);
		centerWrapper.add(actions, BorderLayout.EAST);

		card.add(centerWrapper, BorderLayout.CENTER);

		JPanel sizeLimiter = new JPanel(new BorderLayout());
		sizeLimiter.setOpaque(false);
		sizeLimiter.setPreferredSize(new Dimension(840, 520));
		sizeLimiter.add(card, BorderLayout.CENTER);

		add(sizeLimiter, BorderLayout.CENTER);
	}

	public void refresh() {
		model.setVehicules(fenetre.getUtilisateur().getVehicules());
		model.fireTableDataChanged();
	}

	public Vehicule getSelectedVehicule() {
		int sel = table.getSelectedRow();
		if (sel == -1)
			return null;
		return model.getVehiculeAt(table.convertRowIndexToModel(sel));
	}

	/* Table model for Vehicule */

	private static class VehiculeTableModel extends AbstractTableModel {
		private final String[] cols = { "Désignation", "Type", "Immatriculation", "Hauteur (m)" };
		private Vector<Vehicule> data = new Vector<>();

		VehiculeTableModel(Set<Vehicule> vehicules) {
			setVehicules(vehicules);
		}

		void setVehicules(Set<Vehicule> vehicules) {
			data.clear();
			if (vehicules != null)
				data.addAll(vehicules);
		}

		Vehicule getVehiculeAt(int row) {
			return data.get(row);
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return cols.length;
		}

		@Override
		public String getColumnName(int column) {
			return cols[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Vehicule v = data.get(rowIndex);
			switch (columnIndex) {
				case 0:
					return v.getDesignation();
				case 1:
					return v.getType().toString();
				case 2:
					return v.getImmatriculation();
				case 3:
					return v.getHauteur();
				default:
					return "";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 3)
				return Float.class;
			return String.class;
		}
	}

	/* Helper styled button (same as Accueil) */

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
}
