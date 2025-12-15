package vue;

import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import modele.dao.DaoUtilisateur;

public class HistoriqueStationnement extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private FenetrePrincipal fenetreprincipal;

	/**
	 * Create the panel.
	 */
	public HistoriqueStationnement(FenetrePrincipal fenetreprincipal) {
    	this.fenetreprincipal = fenetreprincipal;
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
		
		DefaultTableModel modele = new DefaultTableModel();
		modele.addColumn("Zone");
		modele.addColumn("Parking");
		modele.addColumn("Véhicule");
		modele.addColumn("Arrivée");
		modele.addColumn("Sortie");
		String[] titleRow = {"Zone", "Parking", "Véhicule", "Arrivée", "Sortie"};
		modele.addRow(titleRow);
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				String[] newRow = {String.valueOf(res.getInt(1)), "Parking", res.getString(3), "Arrivée", "Sortie"};
				modele.addRow(newRow);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		table = new JTable();
		table.setModel(modele);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		paneCenter.add(table);

	}

}
