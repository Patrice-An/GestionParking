package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		String vehiculeId = (String) b.getClientProperty("vehicule");
		int ParkingId = (int) b.getClientProperty("parking");
		DaoVehicule daoV = new DaoVehicule();
		DaoUtilisateur daoU = new DaoUtilisateur();
		DaoParking daoP = new DaoParking();
		Optional<Vehicule> vehicule = java.util.Optional.empty();
		Optional<Utilisateur> user = java.util.Optional.empty();
		Optional<Parking> parking = java.util.Optional.empty();
		try {
			vehicule = daoV.findById(vehiculeId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parking = daoP.findById(ParkingId);
		if (b.getText() == "Partir") {
			
			JOptionPane.showMessageDialog(null, "Le véhicule : "+vehicule.get().getImmatriculation()+" doit partir");
			daoP.quitterParking(ParkingId, b.getClientProperty("arrivee").toString());
		}
		if (b.getText() == "Facture") {

			int userID = vehicule.get().getId_utilisateur();
			try {
				user = daoU.findById(userID);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			donneesFacture.add(user.get().getNom());
			donneesFacture.add(user.get().getPrenom());
			donneesFacture.add(vehicule.get().toString());
			donneesFacture.add(b.getClientProperty("parking").toString());
			donneesFacture.add(b.getClientProperty("arrivee").toString());
			donneesFacture.add(b.getClientProperty("depart").toString());
			
			Facture facture = new Facture(this.fenPrincipal, this);
			JOptionPane.showMessageDialog(null, facture);
			donneesFacture = new ArrayList<>();
		}
	}
	
	public List<String> getDonneesFacture() {
		return this.donneesFacture;
	}

}
