package modele.dao.requetes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;

public class RequeteSelectIdUtilisateurByNomAndPassword extends Requete<Utilisateur> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "select * from utilisateur where nom = ? and password = ?";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
	}

}
