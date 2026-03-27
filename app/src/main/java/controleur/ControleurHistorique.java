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
import vue.App;
import vue.App.VUE;
import vue.Facture;
import vue.HistoriqueStationnement;

public class ControleurHistorique implements ActionListener {
	
	private final App fenPrincipal;
	private final HistoriqueStationnement vue;
	private List<String> donneesFacture = new ArrayList<>();

	public ControleurHistorique(HistoriqueStationnement vue, App fenPrincipal) {
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
		Optional<Vehicule> vehiculeOptional = Optional.empty();
		try {
			vehiculeOptional = daoV.findById(vehiculeImmat);
		} catch (SQLException e1) {
			//
		}
		
		Vehicule vehicule;
		Utilisateur user;
		
		if (!vehiculeOptional.isEmpty()) {
			vehicule = vehiculeOptional.get();
		} else {
			return;
		}

		Optional<Utilisateur> userOptional = Optional.empty();
		
		try {
			userOptional = daoU.findById(vehicule.getId_utilisateur());
		} catch (SQLException e1) {
			//
		}

		
		if (!userOptional.isEmpty()) {
			user = userOptional.get();
		} else {
			return;
		}

		if (b.getText().equals("Partir")) {
			String arrivee = b.getClientProperty("arrivee").toString();
			try {
				daoP.quitterParking(parkingId, arrivee);
				JOptionPane.showMessageDialog(null, "Le véhicule : "+vehicule.getImmatriculation()+" est partie", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
				vue.refresh();
			} catch (SQLException e1) {
				//
			}
		}
		if (b.getText().equals("Facture")) {
			donneesFacture.add(user.getNom());
			donneesFacture.add(user.getPrenom());
			donneesFacture.add(vehicule.toString());

			try {
				donneesFacture.add(daoP.findById(parkingId).get().getNom());
			} catch (SQLException eee) {
				return;
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
