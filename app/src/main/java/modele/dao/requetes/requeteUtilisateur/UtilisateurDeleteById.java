package modele.dao.requetes.requeteUtilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class UtilisateurDeleteById extends Requete<Utilisateur> {


    @Override
	public String requete() {
		return "delete from utilisateur where id_utilisateur = ?";
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}
}
