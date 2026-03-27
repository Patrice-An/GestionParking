package modele.dao.requetes.requeteutilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UtilisateurSelectSeGarer extends Requete<Utilisateur> {


	@Override
	public String requete() {
		return "select * from garer " +
		"join véhicule on véhicule.immat = garer.immat " +
		"where véhicule.id_utilisateur = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
