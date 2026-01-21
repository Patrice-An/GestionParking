package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.Refreshable;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Parking;
import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoParking;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;
import vue.Facture;
import vue.FenetrePrincipal;
import vue.HistoriqueStationnement;
import vue.FenetrePrincipal.VUE;

public class ControleurHistorique implements ActionListener {
	
	private FenetrePrincipal fenPrincipal;
	private HistoriqueStationnement vue;
	private List<String> donneesFacture = new ArrayList<>();

	public ControleurHistorique(HistoriqueStationnement vue, FenetrePrincipal fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String vehiculeImmat = (String) b.getClientProperty("vehiculeImmat");
		int parkingId = (int) b.getClientProperty("parkingID");
		DaoVehicule daoV = new DaoVehicule();
		DaoUtilisateur daoU = new DaoUtilisateur();
		DaoParking daoP = new DaoParking();
		Optional<Vehicule> vehicule = java.util.Optional.empty();
		Optional<Utilisateur> user = java.util.Optional.empty();
		Optional<Parking> parking = java.util.Optional.empty();
		try {
			vehicule = daoV.findById(vehiculeImmat);
			parking = daoP.findById(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (b.getText() == "Partir") {
			String arrivee = b.getClientProperty("arrivee").toString();
			try {
				daoP.quitterParking(parkingId, arrivee);
				JOptionPane.showMessageDialog(null, "Le véhicule : "+vehicule.get().getImmatriculation()+" est partie", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
				vue.refresh();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (b.getText() == "Facture") {
			int userID = vehicule.get().getId_utilisateur();
			try {
				user = daoU.findById(userID);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			donneesFacture.add(user.get().getNom());
			donneesFacture.add(user.get().getPrenom());
			donneesFacture.add(vehicule.get().toString());
			try {
				donneesFacture.add(daoP.findById(parkingId).get().getNom());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			donneesFacture.add(b.getClientProperty("arrivee").toString());
			donneesFacture.add(b.getClientProperty("depart").toString());
			
			Facture facture = new Facture(this.fenPrincipal, this);
			JOptionPane.showMessageDialog(null, facture);
			donneesFacture = new ArrayList<>();
		}
		fenPrincipal.ChangementVue(VUE.ACCUEIL);
	}
	
	public List<String> getDonneesFacture() {
		return this.donneesFacture;
	}

}
