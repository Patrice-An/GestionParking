package vue;

import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;

import modele.Parking;
import modele.Zone;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;

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
				Optional<Zone> zone = new DaoZone().findById(res.getInt(1));
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (zone.isPresent()) {
					String[] newRow = {zone.get().getCouleur(), "Parking", res.getString(3), res.getString(5), res.getString(6)};
					modele.addRow(newRow);
				} else if (parking.isPresent()) {
					String[] newRow = {"Zone", parking.get().getNom(), res.getString(3), res.getString(5), res.getString(6)};
					modele.addRow(newRow);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(modele);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setPreferredSize(new Dimension(800, 400));
		table.setMinimumSize(new Dimension(400, 200));
		table.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(table);

	}

}
