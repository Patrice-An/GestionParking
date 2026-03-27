package modele.dao.requetes.requeteutilisateur;

import modele.Utilisateur;
import modele.dao.requetes.Requete;

public class SelectAllUtilisateur extends Requete<Utilisateur> {

	@Override
	public String requete() {
		return "select * from utilisateur";
	}
}
