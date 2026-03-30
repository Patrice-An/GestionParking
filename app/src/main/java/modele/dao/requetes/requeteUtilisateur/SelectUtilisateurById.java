package modele.dao.requetes.requeteUtilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class SelectUtilisateurById extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "select * from utilisateur where id_utilisateur = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

}
