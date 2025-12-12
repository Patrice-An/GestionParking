package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.FenetrePrincipal;
import main.java.vue.Inscription;
import main.java.vue.SeConnecter;
import modele.Utilisateur;
import modele.dao.DaoUtilisateur;

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
					fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "accueil" );
				}
			} catch (SQLException exeption) {
				return;
			}
		} else if (b.getText() == "S'inscrire") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "inscription" );
		}
	}
}
