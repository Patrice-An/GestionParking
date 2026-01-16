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
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;
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
		DaoVehicule daoV = new DaoVehicule();
		DaoUtilisateur daoU = new DaoUtilisateur();
		Optional<Vehicule> vehicule = java.util.Optional.empty();
		try {
			vehicule = daoV.findById(vehiculeId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (b.getText() == "Partir") {
			
			JOptionPane.showMessageDialog(null, "Le véhicule : "+vehicule.get().getImmatriculation()+" doit partir");
		}
		if (b.getText() == "Facture") {
			Optional<Utilisateur> user = java.util.Optional.empty();
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
			
			System.out.println(donneesFacture);
			fenPrincipal.ChangementVue(VUE.FACTURE);
		}
	}

}
