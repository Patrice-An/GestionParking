package modele.dao.requetes.requetezone;

import modele.Zone;
import modele.dao.requetes.Requete;

public class ZoneSelectAll extends Requete<Zone> {

    

    @Override
	public String requete()  {
		return "select * from zone";
		
	}
}