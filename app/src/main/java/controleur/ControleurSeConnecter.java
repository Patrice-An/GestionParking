package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.App;
import vue.SeConnecter;
import vue.App.VUE;
import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;

public class ControleurSeConnecter implements ActionListener{

	private SeConnecter vue;
	private App App;
			
	public ControleurSeConnecter(SeConnecter vue, App App) {
		this.App = App;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Se connecter")) {
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
					App.setUtilisateur(utilisateur);
					App.ChangementVue(VUE.ACCUEIL);
				} else {
					JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect", "Formulaire erroné", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException eee) {
				//empty
			}
		} else if (b.getText().equals("S'inscrire")) {
			App.ChangementVue(VUE.INSCRIPTION);
		}
	}
}
