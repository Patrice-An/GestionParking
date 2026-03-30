package modele.dao.requetes.requeteParking;

import modele.Parking;
import modele.dao.requetes.Requete;

public class ParkingSelectAll extends Requete<Parking> {

    @Override
	public String requete()  {
		return "select * from parking";
	}

}
