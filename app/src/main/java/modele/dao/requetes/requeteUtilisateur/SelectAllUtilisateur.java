package modele.dao.requetes.requeteUtilisateur;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class SelectAllUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "select * from utilisateur";
	}
}
