package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vue.FenetrePrincipal;
import vue.Inscription;
import vue.SeConnecter;
import vue.FenetrePrincipal.VUE;
import modele.Utilisateur;
import modele.dao.DaoUtilisateur;

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
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate naissance = LocalDate.parse(vue.getNaissanceField().getText(), formatter);
					String tel = vue.getTelField().getText();
					String password = String.valueOf(vue.getPasswordField().getPassword());
					Utilisateur utilisateur = new Utilisateur(0, nom, prenom, tel, naissance, mail, password);
					try {
						DaoUtilisateur dao = new DaoUtilisateur();
						if (dao.emailUnique(mail)) {
							dao.create(utilisateur);
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
