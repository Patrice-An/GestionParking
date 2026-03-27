package modele.dao.requetes.requetevehicule;

import modele.Vehicule;
import modele.dao.requetes.Requete;

public class VehiculeSelectAll extends Requete<Vehicule> {

    

    @Override
	public String requete()  {
		return "select * from vehicule";
		
	}
}
