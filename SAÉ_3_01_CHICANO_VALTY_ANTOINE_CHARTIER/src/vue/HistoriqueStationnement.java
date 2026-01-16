package vue;

import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

import controleur.ControleurHistorique;

import modele.Parking;
import modele.Zone;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Rectangle;

public class HistoriqueStationnement extends JPanel {

	private static final long serialVersionUID = 1L;
    private FenetrePrincipal fenetreprincipal;
    private ControleurHistorique controleur;
	private JPanel paneParking;
	private JPanel paneZone;
	private JButton btnPartir;

	/**
	 * Create the panel.
	 */
	public HistoriqueStationnement(FenetrePrincipal fenetreprincipal) {
		
    	this.fenetreprincipal = fenetreprincipal;
    	controleur = new ControleurHistorique(this, this.fenetreprincipal);
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
		
		paneParking = new JPanel();
		paneParentParking.add(paneParking);
		
		JLabel lblNewLabel_2 = new JLabel("Dans un parking :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentParking.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JSeparator separator = new JSeparator();
		paneCenter.add(separator);
		
		JPanel paneParentZone = new JPanel();
		paneParentZone.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(paneParentZone);
		paneParentZone.setLayout(new BorderLayout(0, 0));
		
		paneZone = new JPanel();
		paneParentZone.add(paneZone);
		
		JLabel lblNewLabel_1 = new JLabel("En voirie :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentZone.add(lblNewLabel_1, BorderLayout.NORTH);
		this.build();
	}

	public void build() {
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(fenetreprincipal.getUtilisateur().getid());
			while (res.next()) {
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (parking.isPresent()) {
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
					
						
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
						
						JButton btnFacture = new JButton("Facture");
						verticalBox.add(btnFacture);
						btnFacture.setBackground(new Color(0, 128, 192));
						btnFacture.putClientProperty("vehicule", res.getString(3));
						btnFacture.putClientProperty("utilisateur", null);
						btnFacture.putClientProperty("parking", parking.get().getNom());
						btnFacture.putClientProperty("arrivee", res.getString(5));
						btnFacture.putClientProperty("depart", res.getString(6));
						btnFacture.addActionListener(controleur);
					} else {
						JLabel LabelSortie = new JLabel("A : Maintenant");
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
						
						JButton btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.setBackground(new Color(255, 128, 128));
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.putClientProperty("parkingID", parking.get().getId_parking());
						btnPartir.addActionListener(controleur);
					}
					paneParking.add(verticalBox);
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
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
					
						
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
						
						JButton btnFacture = new JButton("Facture");
						verticalBox.add(btnFacture);
						btnFacture.setBackground(new Color(0, 128, 192));
						btnFacture.putClientProperty("vehicule", res.getString(3));
						btnFacture.putClientProperty("utilisateur", null);
						btnFacture.putClientProperty("parking", zone.get().getCouleur());
						btnFacture.putClientProperty("arrivee", res.getString(5));
						btnFacture.putClientProperty("depart", res.getString(6));
						btnFacture.addActionListener(controleur);
					} else {
						JLabel LabelSortie = new JLabel("A : Maintenant");
						LabelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(LabelSortie);
						
						JButton btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.setBackground(new Color(255, 128, 128));
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.addActionListener(controleur);
					}

					paneZone.add(verticalBox);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		paneZone.removeAll();
		paneParking.removeAll();
		build();
	}
	
	public JButton getbtnPartir() {
		return btnPartir;
	}

}
