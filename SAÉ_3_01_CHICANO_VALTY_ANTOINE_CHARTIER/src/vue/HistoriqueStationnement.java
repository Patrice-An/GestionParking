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
	private JTable parkingTable;
	private JTable zoneTable;
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
		
		DefaultTableModel modeleTableParking = new DefaultTableModel();
		modeleTableParking.addColumn("Zone");
		modeleTableParking.addColumn("Parking");
		modeleTableParking.addColumn("Véhicule");
		modeleTableParking.addColumn("Arrivée");
		modeleTableParking.addColumn("Sortie");
		String[] titleRow = {"Parking", "Véhicule", "Arrivée", "Sortie"};
		modeleTableParking.addRow(titleRow);
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (parking.isPresent()) {
					String[] newRow = {parking.get().getNom(), res.getString(3), res.getString(5), res.getString(6)};
					modeleTableParking.addRow(newRow);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		parkingTable = new JTable();
		parkingTable.setRowSelectionAllowed(false);
		parkingTable.setModel(modeleTableParking);
		parkingTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		parkingTable.setPreferredSize(new Dimension(800, 400));
		parkingTable.setMinimumSize(new Dimension(400, 200));
		parkingTable.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(parkingTable);


		DefaultTableModel modeleTableZone = new DefaultTableModel();
		modeleTableZone.addColumn("Zone");
		modeleTableZone.addColumn("Parking");
		modeleTableZone.addColumn("Véhicule");
		modeleTableZone.addColumn("Arrivée");
		modeleTableZone.addColumn("Sortie");
		titleRow = new String[] {"Zone", "Véhicule", "Arrivée", "Sortie"};
		modeleTableZone.addRow(titleRow);
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Zone> zone = new DaoZone().findById(res.getInt(1));
				if (zone.isPresent()) {
					String[] newRow = {zone.get().getCouleur(), res.getString(3), res.getString(5), res.getString(6)};
					modeleTableZone.addRow(newRow);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		zoneTable = new JTable();
		zoneTable.setRowSelectionAllowed(false);
		zoneTable.setModel(modeleTableZone);
		zoneTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		zoneTable.setPreferredSize(new Dimension(800, 400));
		zoneTable.setMinimumSize(new Dimension(400, 200));
		zoneTable.setMaximumSize(new Dimension(1200, 600));
		paneCenter.add(zoneTable);
	}

}
