package modele.dao.requetes.requeteutilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UpdateUtilisateur extends Requete<Utilisateur> {

    @Override
	public String requete() {
		return "";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
