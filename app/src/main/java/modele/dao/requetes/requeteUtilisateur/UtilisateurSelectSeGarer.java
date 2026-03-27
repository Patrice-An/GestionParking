package modele.dao.requetes.requeteutilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UtilisateurSelectSeGarer extends Requete<Utilisateur> {


	@Override
	public String requete() {
		return "select * from garer " +
		"join vehicule on vehicule.immat = garer.immat " +
		"where vehicule.id_utilisateur = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
