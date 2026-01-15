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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;

import controleur.ControleurHistorique;
import modele.Parking;
import modele.Zone;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

public class HistoriqueStationnement extends JPanel {

	private static final long serialVersionUID = 1L;
    private FenetrePrincipal fenetreprincipal;
    private ControleurHistorique controleur;

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
		paneCenter.setLayout(new BoxLayout(paneCenter, BoxLayout.Y_AXIS));
		
		JPanel paneParentParking = new JPanel();
		paneParentParking.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(paneParentParking);
		paneParentParking.setLayout(new BorderLayout(0, 0));
		
		JPanel paneParking = new JPanel();
		paneParentParking.add(paneParking);
		
		JLabel lblNewLabel_2 = new JLabel("Dans un parking :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentParking.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		paneCenter.add(separator);
		
		JPanel paneParentZone = new JPanel();
		paneParentZone.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(paneParentZone);
		paneParentZone.setLayout(new BorderLayout(0, 0));
		
		JPanel paneZone = new JPanel();
		paneParentZone.add(paneZone);
		
		JLabel label = new JLabel("New label");
		paneParentZone.add(label, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("En voirie :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentZone.add(lblNewLabel_1, BorderLayout.NORTH);
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (parking.isPresent()) {
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
					paneParking.add(verticalBox);
						
					JLabel LabelNom = new JLabel("Parking : " +parking.get().getNom());
					LabelNom.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelNom);
						
					JLabel LabelVehicule = new JLabel("Vehicule : "+res.getString(3));
					LabelVehicule.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelVehicule);
					
					JLabel LabelArrivee = new JLabel("De : "+res.getString(5));
					LabelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelArrivee);
					
					if (res.getString(6) != null) {
						JLabel LabelSortie = new JLabel("A :"+res.getString(6));
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
					} else {
						JLabel LabelSortie = new JLabel("A : Maintenant");
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
						
						JButton btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.addActionListener(controleur);
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Zone> zone = new DaoZone().findById(res.getInt(1));
				if (zone.isPresent()) {
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
					paneZone.add(verticalBox);
						
					JLabel LabelNom = new JLabel("Zone : " +zone.get().getCouleur());
					LabelNom.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelNom);
						
					JLabel LabelVehicule = new JLabel("Vehicule : "+res.getString(3));
					LabelVehicule.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelVehicule);
					
					JLabel LabelArrivee = new JLabel("De : "+res.getString(5));
					LabelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(LabelArrivee);
					
					if (res.getString(6) != null) {
						JLabel LabelSortie = new JLabel("A :"+res.getString(6));
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
					} else {
						JLabel LabelSortie = new JLabel("A : Maintenant");
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
						
						JButton btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.addActionListener(controleur);
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void refresh() {
		System.out.println("Tu as pas terminé le refresh de Historique stationnemment");
	
	}

}
