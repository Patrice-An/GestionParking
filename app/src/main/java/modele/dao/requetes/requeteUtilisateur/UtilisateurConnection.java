package modele.dao.requetes.requeteutilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UtilisateurConnection extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "select * from utilisateur where mail = ? and password = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
		prSt.setString(2, id[1]);
	}

}
