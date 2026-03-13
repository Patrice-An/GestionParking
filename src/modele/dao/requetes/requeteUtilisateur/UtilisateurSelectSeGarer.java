package modele.dao.requetes.requeteUtilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UtilisateurSelectSeGarer extends Requete<Utilisateur> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "select * from garer " +
		"join véhicule on véhicule.immat = garer.immat " +
		"where véhicule.id_utilisateur = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
