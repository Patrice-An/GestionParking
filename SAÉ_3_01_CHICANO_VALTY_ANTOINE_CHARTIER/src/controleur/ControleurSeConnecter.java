package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;

import main.java.vue.FenetrePrincipal;
import main.java.vue.SeConnecter;
import main.java.vue.FenetrePrincipal.VUE;
import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;

public class ControleurSeConnecter implements ActionListener{

	private SeConnecter vue;
	private FenetrePrincipal fenetrePrincipal;
			
	public ControleurSeConnecter(SeConnecter vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Se connecter") {
			String email = vue.getEmail();
			String password = vue.getPassword();
			try {
				Optional<Utilisateur> user = new DaoUtilisateur().utilisateurCanConnect(email, password);
				if (user.isPresent()) {
					Utilisateur utilisateur = user.get();
					List<Vehicule> vehiculeUtilisateur = new DaoVehicule().findVehiculeByUser(utilisateur.getid());
					for (Vehicule v : vehiculeUtilisateur) {
						utilisateur.addVehicule(v);
					}
					fenetrePrincipal.setUtilisateur(utilisateur);
					fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		} else if (b.getText() == "S'inscrire") {
			fenetrePrincipal.ChangementVue(VUE.INSCRIPTION);
		}
	}
}
