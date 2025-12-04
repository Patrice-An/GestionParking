package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteSelectIdUtilisateurByNomUtilisateur extends Requete<Utilisateur> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "select * from utilisateur where nom = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(0, id[0]);
	}

}
