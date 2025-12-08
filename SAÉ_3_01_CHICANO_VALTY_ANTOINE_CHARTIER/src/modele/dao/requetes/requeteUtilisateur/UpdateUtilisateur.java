package modele.dao.requetes.requeteUtilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UpdateUtilisateur extends Requete<Utilisateur> {

    private String requete;

    @Override
	public String requete() {
		this.requete = "";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
