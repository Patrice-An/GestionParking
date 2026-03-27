package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;
import vue.App;
import vue.App.VUE;
import vue.SeConnecter;

public class ControleurSeConnecter implements ActionListener{

	private final SeConnecter vue;
	private App app;
			
	public ControleurSeConnecter(SeConnecter vue) {
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
					app.setUtilisateur(utilisateur);
					app.ChangementVue(VUE.ACCUEIL);
				} else {
					JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect", "Formulaire erroné", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException eee) {
				//empty
			}
		} else if (b.getText().equals("S'inscrire")) {
			app.ChangementVue(VUE.INSCRIPTION);
		}
	}
}
