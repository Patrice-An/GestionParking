package modele.dao.requetes.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class SelectAllUtilisateur extends Requete<Utilisateur> {

    private String requete;

	@Override
	public String requete() {
		this.requete = "select * from utilisateur";
		return this.requete;
	}
	
	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
	}

}
