package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
		switch (b.getText()) {
			case "Valider":
				validerInscription();
				break;
			case "Connectez-vous":
				fenetrePrincipal.ChangementVue(VUE.SECONNECTER);
				break;
			default:
				break;
		}
	}

	public void validerInscription() {

		final String FORMULAIRE_INVALIDE_MESSAGE = "Formulaire invalide";

		if (vue.getNomField().getText().isEmpty()
			|| vue.getPrenomField().getText().isEmpty()
			|| vue.getMailField().getText().isEmpty()
			|| vue.getTelField().getText().isEmpty()
			|| vue.getPasswordField().getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Champs non remplis", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		String nom = vue.getNomField().getText();
		if(contientNombre(nom)){
			JOptionPane.showMessageDialog(null, "Nom invalide", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String prenom = vue.getPrenomField().getText();
		if(contientNombre(prenom)){
			JOptionPane.showMessageDialog(null, "Prenom invalide", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String mail = vue.getMailField().getText();
		if (!bonFormattageMail(mail)) {
			JOptionPane.showMessageDialog(null, "Mail invalide", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		JDatePickerImpl naissancePicker = vue.getNaissancePicker();
		UtilDateModel dateModel = (UtilDateModel) naissancePicker.getModel();
		if (dateModel.getYear() > 2010) {
			JOptionPane.showMessageDialog(null, "Date de naissance invalide", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		LocalDate naissance = LocalDate.of(dateModel.getYear(), dateModel.getMonth()+1, dateModel.getDay());
		String tel = vue.getTelField().getText();
		if(!bonFormattageTelephone(tel)) {
			JOptionPane.showMessageDialog(null, "Numéro de téléphone invalide", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String password = String.valueOf(vue.getPasswordField().getPassword());
		Utilisateur utilisateur = new Utilisateur(0, nom, prenom, tel, naissance, mail, password);
		try {
			DaoUtilisateur dao = new DaoUtilisateur();
			if (dao.emailUnique(mail)) {
				dao.create(utilisateur);
				Optional<Utilisateur> optionalUser = new DaoUtilisateur().utilisateurCanConnect(mail, password);
				if (optionalUser.isEmpty()) {
					return;
				}
				utilisateur = optionalUser.get();
				List<Vehicule> vehiculeUtilisateur = new DaoVehicule().findVehiculeByUser(utilisateur.getid());
				for (Vehicule v : vehiculeUtilisateur) {
					utilisateur.addVehicule(v);
				}
				fenetrePrincipal.setUtilisateur(utilisateur);
				fenetrePrincipal.ChangementVue(VUE.ACCUEIL);
				JOptionPane.showMessageDialog(null, "Inscription effectuée !", "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Email déjà utilisé", FORMULAIRE_INVALIDE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	private boolean bonFormattageTelephone(String mot) {
		Pattern pattern = Pattern.compile("^(\\+|00|0)[1-9][0-9]{6,14}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(mot);
	    return matcher.find();
	}
	
	private boolean contientNombre(String mot) {
		Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(mot);
	    return matcher.find();
	}
	
	private boolean bonFormattageMail(String mail) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+.[a-zA-Z]{2,}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(mail);
	    return matcher.find();
	}
	
}