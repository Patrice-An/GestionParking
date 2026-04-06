package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;
import modele.dao.DaoZone;
import vue.Facture;
import vue.App;
import vue.HistoriqueStationnement;
import vue.App.VUE;

public class ControleurHistorique implements ActionListener {

	private App fenPrincipal;
	private HistoriqueStationnement vue;
	private List<String> donneesFacture = new ArrayList<>();
	private boolean isParking;

	public ControleurHistorique(HistoriqueStationnement vue, App fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String vehiculeImmat = (String) b.getClientProperty("vehiculeImmat");
		DaoVehicule daoV = new DaoVehicule();
		DaoUtilisateur daoU = new DaoUtilisateur();
		Optional<Vehicule> vehiculeOptional = Optional.empty();
		try {
			vehiculeOptional = daoV.findById(vehiculeImmat);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Vehicule vehicule;
		Utilisateur user;
		if (vehiculeOptional.isPresent()) {
			vehicule = vehiculeOptional.get();
		} else {
			return;
		}
		Optional<Utilisateur> userOptional = Optional.empty();

		try {
			userOptional = daoU.findById(vehicule.getidUtilisateur());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} else {
			return;
		}
		this.isParking = (boolean) b.getClientProperty("isParking");
		if (this.isParking) {
			int parkingId = (int) b.getClientProperty("parkingID");
			DaoParking daoP = new DaoParking();
			if (b.getText().equals("Partir")) {
				String arrivee = b.getClientProperty("arrivee").toString();
				try {
					if (isParking) {
						daoP.quitterParking(parkingId, arrivee);
					}

					JOptionPane.showMessageDialog(null,
							"Le véhicule : " + vehicule.getImmatriculation() + " est partie", "Opération réussie",
							JOptionPane.INFORMATION_MESSAGE);
					vue.refresh();
					fenPrincipal.changementVue(VUE.ACCUEIL);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (b.getText().equals("Facture")) {
				donneesFacture.add(user.getNom());
				donneesFacture.add(user.getPrenom());
				donneesFacture.add(vehicule.toString());

				try {
					donneesFacture.add(daoP.findById(parkingId).get().getNom());
				} catch (SQLException ee) {
					return;
				}
				donneesFacture.add(b.getClientProperty("arrivee").toString());
				donneesFacture.add(b.getClientProperty("depart").toString());

				Facture facture = new Facture(this);
				JOptionPane.showMessageDialog(null, facture);
				donneesFacture = new ArrayList<>();
			}

		} else {
			int zoneId = (int) b.getClientProperty("zoneCouleur");
			DaoZone daoZ = new DaoZone();
			if (b.getText().equals("Facture")) {
				donneesFacture.add(user.getNom());
				donneesFacture.add(user.getPrenom());
				donneesFacture.add(vehicule.toString());

				try {
					donneesFacture.add(daoZ.findById(zoneId).get().getCouleur());
				} catch (SQLException ee) {
					return;
				}
				donneesFacture.add(b.getClientProperty("arrivee").toString());
				donneesFacture.add(b.getClientProperty("depart").toString());

				Facture facture = new Facture(this);
				JOptionPane.showMessageDialog(null, facture);
				donneesFacture = new ArrayList<>();
			}
		}
	}

	public List<String> getDonneesFacture() {
		return this.donneesFacture;
	}

	public boolean isParking() {
		return this.isParking;
	}
}
