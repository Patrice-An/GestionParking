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

public class HistoriqueStationnement extends JPanel {

	private static final long serialVersionUID = 1L;
	private final App app;
	private final transient ControleurHistorique controleur;
	private final JPanel paneParking;
	private final JPanel paneZone;
	private JButton btnPartir;

	/**
	 * Create the panel.
	 */
	public HistoriqueStationnement(App app) {

		this.app = app;
		controleur = new ControleurHistorique(this, this.app);
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

		JLabel lblNewLabel2 = new JLabel("Dans un parking :");
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentParking.add(lblNewLabel2, BorderLayout.NORTH);

		JSeparator separator = new JSeparator();
		paneCenter.add(separator);

		JPanel paneParentZone = new JPanel();
		paneParentZone.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneCenter.add(paneParentZone);
		paneParentZone.setLayout(new BorderLayout(0, 0));

		paneZone = new JPanel();
		paneParentZone.add(paneZone);

		JLabel lblNewLabel1 = new JLabel("En voirie :");
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		paneParentZone.add(lblNewLabel1, BorderLayout.NORTH);
		this.build();
	}

	private void build() {
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(app.getUtilisateur().getid());
			System.out.println(res.toString());
			while (res.next()) {
				Optional<Parking> parking = new DaoParking().findById(res.getInt(2));
				if (parking.isPresent()) {
					System.out.println("PARKING IS PRESENT");
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

					JLabel labelNom = new JLabel("Parking : " + parking.get().getNom());
					labelNom.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelNom);

					JLabel labelVehicule = new JLabel("Vehicule : " + res.getString(3));
					labelVehicule.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelVehicule);

					JLabel labelArrivee = new JLabel("De : " + res.getString(5));
					labelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelArrivee);

					if (res.getString(6) != null) {
						JLabel labelSortie = new JLabel("A :" + res.getString(6));
						labelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(labelSortie);

						JButton btnFacture = new JButton("Facture");
						verticalBox.add(btnFacture);
						btnFacture.setBackground(new Color(0, 128, 192));
						btnFacture.putClientProperty("vehiculeImmat", res.getString(3));
						btnFacture.putClientProperty("utilisateur", null);
						btnFacture.putClientProperty("parkingID", parking.get().getidParking());
						btnFacture.putClientProperty("arrivee", res.getString(5));
						btnFacture.putClientProperty("depart", res.getString(6));
						btnFacture.addActionListener(controleur);
					} else {
						JLabel labelSortie = new JLabel("A : Maintenant");
						labelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(labelSortie);

						JButton btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.setBackground(new Color(255, 128, 128));
						btnPartir.putClientProperty("vehiculeImmat", res.getString(3));
						btnPartir.putClientProperty("arrivee", res.getString(5));
						btnPartir.putClientProperty("parkingID", parking.get().getidParking());
						btnPartir.addActionListener(controleur);
					}
					paneParking.add(verticalBox);
				}
			}
		} catch (SQLException eee) {
			eee.printStackTrace();
		}
		try {
			ResultSet res = new DaoUtilisateur().garerByUtilisateur(app.getUtilisateur().getid());
			while (res.next()) {
				Optional<Zone> zone = new DaoZone().findById(res.getInt(1));
				if (zone.isPresent()) {
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

					JLabel labelNom = new JLabel("Zone : " + zone.get().getCouleur());
					labelNom.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelNom);

					JLabel labelVehicule = new JLabel("Vehicule : " + res.getString(3));
					labelVehicule.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelVehicule);

					JLabel labelArrivee = new JLabel("De : " + res.getString(5));
					labelArrivee.setHorizontalAlignment(SwingConstants.CENTER);
					verticalBox.add(labelArrivee);

					if (res.getString(6) != null) {
						JLabel labelSortie = new JLabel("A :" + res.getString(6));
						labelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(labelSortie);

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
						JLabel labelSortie = new JLabel("A : Maintenant");
						labelSortie.setHorizontalAlignment(SwingConstants.CENTER);
						verticalBox.add(labelSortie);

						btnPartir = new JButton("Partir");
						verticalBox.add(btnPartir);
						btnPartir.setBackground(new Color(255, 128, 128));
						btnPartir.putClientProperty("vehicule", res.getString(3));
						btnPartir.addActionListener(controleur);
					}

					paneZone.add(verticalBox);
				}
			}
		} catch (SQLException eee) {
			eee.printStackTrace();
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
