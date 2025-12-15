package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.FenetrePrincipal;
import vue.Inscription;
import vue.FenetrePrincipal.VUE;
import modele.Utilisateur;
import modele.Vehicule;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoVehicule;

public class ControleurInscription implements ActionListener{
	
	private FenetrePrincipal fenetrePrincipal;

	public ControleurInscription(Inscription vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
	}

	Inscription vue;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Valider")) {
			if (!vue.getNomField().getText().isEmpty()
				&& !vue.getPrenomField().getText().isEmpty()
				&& !vue.getMailField().getText().isEmpty()
				&& !vue.getNaissanceField().getText().isEmpty()
				&& !vue.getTelField().getText().isEmpty()
				&& vue.getPasswordField().getPassword().length > 0) {
					String nom = vue.getNomField().getText();
					String prenom = vue.getPrenomField().getText();
					String mail = vue.getMailField().getText();
					LocalDate naissance;
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						naissance = LocalDate.parse(vue.getNaissanceField().getText(), formatter);
					} catch (DateTimeParseException dateException) {
						JOptionPane.showMessageDialog(null, "Date de naissance invalide", "Formulaire invalide", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					String tel = vue.getTelField().getText();
					String password = String.valueOf(vue.getPasswordField().getPassword());
					Utilisateur utilisateur = new Utilisateur(0, nom, prenom, tel, naissance, mail, password);
					try {
						DaoUtilisateur dao = new DaoUtilisateur();
						if (dao.emailUnique(mail)) {
							dao.create(utilisateur);
							Optional<Utilisateur> trueUser = new DaoUtilisateur().utilisateurCanConnect(mail, password);
							utilisateur = trueUser.get();
							List<Vehicule> vehiculeUtilisateur = new DaoVehicule().findVehiculeByUser(utilisateur.getid());
							for (Vehicule v : vehiculeUtilisateur) {
								utilisateur.addVehicule(v);
							}
							fenetrePrincipal.setUtilisateur(utilisateur);
							fenetrePrincipal.ChargerVueDependante();
							fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
							JOptionPane.showMessageDialog(null, "Inscription effectuée !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Email déjà utilisé", "Formulaire invalide", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
					
			}
			
		} else if (b.getText().equals("Connectez-vous")) {
			fenetrePrincipal.ChangementVue(VUE.SECONNECTER);
		}
	}

}
