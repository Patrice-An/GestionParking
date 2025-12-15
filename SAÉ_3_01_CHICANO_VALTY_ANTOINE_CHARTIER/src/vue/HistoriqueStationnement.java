package vue;

import javax.swing.JPanel;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class HistoriqueStationnement extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public HistoriqueStationnement() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Historique de stationnement");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel paneSouth = new JPanel();
		add(paneSouth, BorderLayout.SOUTH);
		paneSouth.setLayout(new BorderLayout(0, 0));
		
		JButton btnPrec = new JButton("Annuler");
		btnPrec.setHorizontalAlignment(SwingConstants.LEFT);
		paneSouth.add(btnPrec, BorderLayout.WEST);
		
		JPanel paneWest = new JPanel();
		add(paneWest, BorderLayout.WEST);
		
		JPanel paneEast = new JPanel();
		add(paneEast, BorderLayout.EAST);
		
		JPanel paneCenter = new JPanel();
		add(paneCenter, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Adresse", "V\u00E9hicule", "Dur\u00E9e", "Date"},
			},
			new String[] {
				"Adresse", "V\u00E9hicule", "Dur\u00E9e", "Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(table);

	}

}
